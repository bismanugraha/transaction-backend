package com.transaction.nutech.service;

import com.transaction.nutech.mapper.ServicesMapper;
import com.transaction.nutech.model.entity.BannerEntity;
import com.transaction.nutech.model.entity.ServicesEntity;
import com.transaction.nutech.model.response.ServicesResponse;
import com.transaction.nutech.model.response.GenericResponse;
import com.transaction.nutech.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.transaction.nutech.constant.Queries.GET_ALL_SERVICES;

@Service
public class ServicesService {
    @Autowired
    GenericRepository genericRepository;
    @Autowired
    ServicesMapper servicesMapper;

    public ResponseEntity<GenericResponse<List<ServicesResponse>>> getAllServices() {
        List<ServicesResponse> servicesResponses = genericRepository.getAllData(GET_ALL_SERVICES, null, ServicesEntity.class).stream()
                .map(servicesMapper::mapResponse)
                .toList();
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Sukses", servicesResponses));
    }
}
