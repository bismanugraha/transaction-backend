package com.transaction.nutech.service;

import com.transaction.nutech.mapper.UserMapper;
import com.transaction.nutech.model.entity.UserData;
import com.transaction.nutech.model.request.UserRequest;
import com.transaction.nutech.model.response.GenericResponse;
import com.transaction.nutech.repository.UserRepository;
import com.transaction.nutech.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<GenericResponse<Void>> registerUser(UserRequest user) {
        userRepository.save(userMapper.map(user));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.of(HttpStatus.CREATED, "Registrasi berhasil silahkan login", null));
    }

    public ResponseEntity<GenericResponse<Map<String, String>>> loginUser(UserRequest user) {
        try {
            Authentication authentication =
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
}
