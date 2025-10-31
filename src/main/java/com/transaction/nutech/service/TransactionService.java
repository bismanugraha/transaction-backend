package com.transaction.nutech.service;

import com.transaction.nutech.exception.InsufficientFundException;
import com.transaction.nutech.exception.ResourceNotFoundException;
import com.transaction.nutech.mapper.TransactionMapper;
import com.transaction.nutech.model.entity.ServicesEntity;
import com.transaction.nutech.model.entity.TransactionEntity;
import com.transaction.nutech.model.entity.UserEntity;
import com.transaction.nutech.model.request.TransactionRequest;
import com.transaction.nutech.model.response.GenericResponse;
import com.transaction.nutech.model.response.TransactionResponse;
import com.transaction.nutech.repository.GenericRepository;
import com.transaction.nutech.util.InvoiceUtil;
import com.transaction.nutech.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.transaction.nutech.constant.Queries.*;

@Service
public class TransactionService {
    @Autowired
    GenericRepository genericRepository;
    @Autowired
    TransactionMapper transactionMapper;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    InvoiceUtil invoiceUtil;

    public ResponseEntity<GenericResponse<Map<String, Object>>> transaction(String token, TransactionRequest request) {
        Map<String, Object> data = new HashMap<>();
        MapSqlParameterSource params = new MapSqlParameterSource();
        List<ServicesEntity> servicesEntities = genericRepository.getAllData(GET_ALL_SERVICES, null, ServicesEntity.class);
        ServicesEntity service = servicesEntities
                .stream()
                .filter(o -> o.getServiceCode().equalsIgnoreCase(request.getServiceCode().name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Service atau Layanan tidak ditemukan"));

        String email = jwtUtil.parseJwtClaims(token.split(" ")[1]).getSubject();
        long balance;
        params.addValue("email", email);
        try {
            balance = genericRepository.getData(GET_USER_BALANCE, params, UserEntity.class).getBalance();
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User tidak ditemukan");
        }

        if(balance < service.getServiceTariff()) {
            throw new InsufficientFundException("Biaya tidak cukup, harap top up terlebih dahulu");
        }

        balance -= service.getServiceTariff();

        params = new MapSqlParameterSource();
        params.addValue("balance", balance);
        params.addValue("email", email);
        genericRepository.updateData(CHANGE_BALANCE, params);

        String transactionType = "PAYMENT";
        String description = service.getServiceName();
        LocalDate createdOn = LocalDate.now();
        Long transactionId = genericRepository.countData(COUNT_TRANSACTION_HISTORY, null).longValue() + 1L;
        String invoiceNumber = invoiceUtil.invoiceGenerator(transactionType, createdOn.format(DateTimeFormatter.ofPattern("ddMMyyyy")), transactionId);

        params = new MapSqlParameterSource();
        params.addValue("transaction_type", transactionType);
        params.addValue("description", description);
        params.addValue("created_on", Date.valueOf(createdOn));
        params.addValue("total_amount", service.getServiceTariff());
        params.addValue("invoice_number", invoiceNumber);
        params.addValue("user_email", email);
        genericRepository.updateData(TRANSACTION, params);

        data.put("invoice_number", invoiceNumber);
        data.put("service_code", service.getServiceCode());
        data.put("service_name", description);
        data.put("transaction_type", transactionType);
        data.put("total_amount", service.getServiceTariff());
        data.put("created_on", createdOn);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Transaksi berhasil", data));
    }

    public ResponseEntity<GenericResponse<List<TransactionResponse>>> getAllTransactionHistory(String token, Integer offset, Integer limit) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String email = jwtUtil.parseJwtClaims(token.split(" ")[1]).getSubject();
        String query = GET_ALL_TRANSACTION_HISTORY;
        List<TransactionEntity> transactionEntities;

        if(limit != null && limit > 0) {
            if(offset != null && offset < 0) {
                offset = 0;
            }
            query += LIMIT_AND_OFFSET;
            params.addValue("limit", limit);
            params.addValue("offset", offset);
        }
        params.addValue("user_email", email);
        try {
            transactionEntities = genericRepository.getAllData(query, params, TransactionEntity.class);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(GenericResponse.of(HttpStatus.OK, "Belum melakukan transaksi", null));
        }

        List<TransactionResponse> response = transactionEntities.stream()
                .map(transactionMapper::map)
                .toList();;

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Get History Berhasil", response));
    }
}
