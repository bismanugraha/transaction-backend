package com.transaction.nutech.mapper;

import com.transaction.nutech.config.SecurityConfig;
import com.transaction.nutech.model.entity.UserData;
import com.transaction.nutech.model.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    SecurityConfig securityConfig;

    public UserData map(UserRequest userRequest) {
        String password = securityConfig.passwordEncoder().encode(userRequest.getPassword());
        return new UserData(
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                password);
    }
}
