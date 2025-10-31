package com.transaction.nutech.service;

import com.transaction.nutech.config.SecurityConfig;
import com.transaction.nutech.config.UserConfig;
import com.transaction.nutech.exception.ResourceNotFoundException;
import com.transaction.nutech.mapper.UserMapper;
import com.transaction.nutech.model.entity.UserEntity;
import com.transaction.nutech.model.request.TopUpRequest;
import com.transaction.nutech.model.request.UserRequest;
import com.transaction.nutech.model.response.GenericResponse;
import com.transaction.nutech.model.response.UserResponse;
import com.transaction.nutech.repository.GenericRepository;
import com.transaction.nutech.util.InvoiceUtil;
import com.transaction.nutech.util.JwtUtil;
import com.transaction.nutech.validator.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.transaction.nutech.constant.Queries.*;

@Service
public class UserService {

    @Autowired
    GenericRepository genericRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserConfig userConfig;
    @Autowired
    ImageValidator imageValidator;
    @Autowired
    SecurityConfig securityConfig;
    @Autowired
    InvoiceUtil invoiceUtil;

    public ResponseEntity<GenericResponse<Void>> registerUser(UserRequest user) {
        String password = securityConfig.passwordEncoder().encode(user.getPassword());

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.getEmail());
        params.addValue("first_name", user.getFirstName());
        params.addValue("last_name", user.getLastName());
        params.addValue("password", password);
        genericRepository.updateData(REGISTER_USER, params);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.of(HttpStatus.CREATED, "Registrasi berhasil silahkan login", null));
    }

    public ResponseEntity<GenericResponse<Map<String, String>>> loginUser(UserRequest user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            String token = jwtUtil.createToken(user);
            Map<String, String> body = new HashMap<>();
            body.put("token", token);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(GenericResponse.of(HttpStatus.OK, "Login sukses", body));
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(GenericResponse.of(HttpStatus.UNAUTHORIZED, "Username atau password salah", null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(GenericResponse.of(HttpStatus.BAD_REQUEST, "Login gagal", null));
        }
    }

    public ResponseEntity<GenericResponse<UserResponse>> getProfile(String token) {
        String email = jwtUtil.parseJwtClaims(token.split(" ")[1]).getSubject();
        UserResponse user = userMapper.mapResponse(getUser(email));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Sukses", user));
    }

    public ResponseEntity<GenericResponse<UserResponse>> updateProfile(String token, UserRequest userRequest) {
        String email = jwtUtil.parseJwtClaims(token.split(" ")[1]).getSubject();
        UserEntity user = getUser(email);
        userMapper.updateProfileMap(user, userRequest);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        params.addValue("first_name", user.getFirstName());
        params.addValue("last_name", user.getLastName());
        genericRepository.updateData(UPDATE_USER, params);

        UserResponse userResponse = userMapper.mapResponse(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Update Pofile berhasil", userResponse));
    }

    public ResponseEntity<GenericResponse<UserResponse>> updateImageProfile(String token, MultipartFile imageFile) throws Exception {
        imageValidator.validate(imageFile);
        String email = jwtUtil.parseJwtClaims(token.split(" ")[1]).getSubject();
        UserEntity user = getUser(email);
        String imageUrl = userConfig.getUrl() + imageFile.getOriginalFilename();
        user.setProfileImage(imageUrl);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        params.addValue("image", imageUrl);

        // Upload to AWS or something

        genericRepository.updateData(UPLOAD_IMAGE, params);
        UserResponse userResponse = userMapper.mapResponse(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Update Profile Image berhasil", userResponse));
    }

    public ResponseEntity<GenericResponse<Map<String, Long>>> getUserBalance(String token) {
        Map<String, Long> data = new HashMap<>();
        String email = jwtUtil.parseJwtClaims(token.split(" ")[1]).getSubject();

        Long balance = getBalance(email);
        data.put("balance", balance);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Get Balance Berhasil", data));
    }

    public ResponseEntity<GenericResponse<Map<String, Long>>> topUp(String token, TopUpRequest topUpBalance) {
        Map<String, Long> data = new HashMap<>();
        String email = jwtUtil.parseJwtClaims(token.split(" ")[1]).getSubject();

        Long balance = getBalance(email);
        balance += topUpBalance.getTopUpBalance();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("balance", balance);
        params.addValue("email", email);
        genericRepository.updateData(CHANGE_BALANCE, params);

        String transactionType = "TOP_UP";
        String description = "Top Up balance";
        LocalDate createdOn = LocalDate.now();
        Long transactionId = genericRepository.countData(COUNT_TRANSACTION_HISTORY, null).longValue() + 1L;
        String invoiceNumber = invoiceUtil.invoiceGenerator(transactionType, createdOn.format(DateTimeFormatter.ofPattern("ddMMyyyy")), transactionId);

        params = new MapSqlParameterSource();
        params.addValue("transaction_type", transactionType);
        params.addValue("description", description);
        params.addValue("created_on", Date.valueOf(createdOn));
        params.addValue("total_amount", topUpBalance.getTopUpBalance());
        params.addValue("invoice_number", invoiceNumber);
        params.addValue("user_email", email);

        genericRepository.updateData(TRANSACTION, params);

        data.put("balance", balance);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Top Up Balance berhasil", data));
    }

    private UserEntity getUser(String email) {
        UserEntity user;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        try {
            user = genericRepository.getData(FIND_USER_BY_EMAIL, params, UserEntity.class);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User tidak ditemukan");
        }

        return user;
    }

    private Long getBalance(String email) {
        long balance;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        try {
            balance = genericRepository.getData(GET_USER_BALANCE, params, UserEntity.class).getBalance();
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User tidak ditemukan");
        }

        return balance;
    }
}
