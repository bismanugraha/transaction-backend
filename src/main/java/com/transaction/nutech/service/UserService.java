package com.transaction.nutech.service;

import com.transaction.nutech.config.UserConfig;
import com.transaction.nutech.exception.UserNotFoundException;
import com.transaction.nutech.mapper.UserMapper;
import com.transaction.nutech.model.entity.UserEntity;
import com.transaction.nutech.model.request.UserRequest;
import com.transaction.nutech.model.response.GenericResponse;
import com.transaction.nutech.model.response.UserResponse;
import com.transaction.nutech.repository.UserRepository;
import com.transaction.nutech.util.JwtUtil;
import com.transaction.nutech.validator.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
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

    public ResponseEntity<GenericResponse<Void>> registerUser(UserRequest user) {
        userRepository.save(userMapper.map(user));
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

        userRepository.save(user);
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

        // Upload to AWS or something

        userRepository.save(user);
        UserResponse userResponse = userMapper.mapResponse(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Update Profile Image berhasil", userResponse));
    }

    private UserEntity getUser(String email) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if(user == null) throw new UserNotFoundException("User tidak ditemukan");

        return user;
    }
}
