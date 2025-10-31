package com.transaction.nutech.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerEntity {
    private int id;
    private String bannerName;
    private String bannerImage;
    private String description;

    public BannerEntity(String bannerName, String bannerImage, String description) {
        this.bannerName = bannerName;
        this.bannerImage = bannerImage;
        this.description = description;
    }
}
