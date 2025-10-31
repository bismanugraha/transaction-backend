package com.transaction.nutech.service;

import com.transaction.nutech.model.entity.UserEntity;
import com.transaction.nutech.repository.GenericRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.transaction.nutech.constant.Queries.CHECK_USER;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final GenericRepository genericRepository;

    public CustomUserDetailsService(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        UserEntity user;
        try {
            user = genericRepository.getData(CHECK_USER, params, UserEntity.class);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User tidak ditemukan");
        }
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
