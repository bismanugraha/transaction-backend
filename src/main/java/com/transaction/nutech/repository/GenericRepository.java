package com.transaction.nutech.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface GenericRepository {
    <T> T getData(String query, MapSqlParameterSource params, Class<T> type);
    Integer countData(String query, MapSqlParameterSource params);
    <T> List<T> getAllData(String query, MapSqlParameterSource params, Class<T> type);
    void updateData(String query, MapSqlParameterSource params); // Create, update, and delete
}
