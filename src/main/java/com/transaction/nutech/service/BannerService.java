package com.transaction.nutech.service;

import com.transaction.nutech.mapper.BannerMapper;
import com.transaction.nutech.model.entity.BannerEntity;
import com.transaction.nutech.model.response.BannerResponse;
import com.transaction.nutech.model.response.GenericResponse;
import com.transaction.nutech.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.transaction.nutech.constant.Queries.GET_ALL_BANNER;

@Service
public class BannerService {
    @Autowired
    GenericRepository genericRepository;
    @Autowired
    BannerMapper bannerMapper;

    public ResponseEntity<GenericResponse<List<BannerResponse>>> getAllBanner() {
        List<BannerResponse> bannerResponses = genericRepository.getAllData(GET_ALL_BANNER, null,BannerEntity.class).stream()
                .map(bannerMapper::mapResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.of(HttpStatus.OK, "Sukses", bannerResponses));
    }
}
