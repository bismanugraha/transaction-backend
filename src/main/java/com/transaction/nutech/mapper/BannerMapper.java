package com.transaction.nutech.mapper;

import com.transaction.nutech.model.entity.BannerEntity;
import com.transaction.nutech.model.response.BannerResponse;
import org.springframework.stereotype.Component;

@Component
public class BannerMapper {
    public BannerResponse mapResponse(BannerEntity bannerEntity) {
        return new BannerResponse(bannerEntity.getBannerName(), bannerEntity.getBannerImage(), bannerEntity.getDescription());
    }
}
