package com.transaction.nutech.repository.impl;

import com.transaction.nutech.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenericRepositoryImpl implements GenericRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public <T> T getData(String query, MapSqlParameterSource params, Class<T> type) {
        return namedParameterJdbcTemplate.queryForObject(query, params, BeanPropertyRowMapper.newInstance(type));
    }

    @Override
    public Integer countData(String query, MapSqlParameterSource params) {
        return namedParameterJdbcTemplate.queryForObject(query, params, Integer.class);
    }

    @Override
    public <T> List<T> getAllData(String query, MapSqlParameterSource params, Class<T> type) {
        return namedParameterJdbcTemplate.query(query, params, BeanPropertyRowMapper.newInstance(type));
    }

    // Create, update, and delete
    @Override
    public void updateData(String query, MapSqlParameterSource params) {
        namedParameterJdbcTemplate.update(query, params);
    }
}
