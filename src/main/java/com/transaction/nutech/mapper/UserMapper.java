package com.transaction.nutech.mapper;

import com.transaction.nutech.config.SecurityConfig;
import com.transaction.nutech.model.entity.UserEntity;
import com.transaction.nutech.model.request.UserRequest;
import com.transaction.nutech.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    SecurityConfig securityConfig;

    public UserEntity map(UserRequest userRequest) {
        String password = securityConfig.passwordEncoder().encode(userRequest.getPassword());
        return new UserEntity(
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                password);
    }

    public void updateProfileMap(UserEntity userEntity, UserRequest userRequest) {
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
    }

    public UserResponse mapResponse(UserEntity userEntity) {
        return new UserResponse(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), userEntity.getProfileImage());
    }
}
