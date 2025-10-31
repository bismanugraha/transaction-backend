package com.transaction.nutech.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesEntity {
    private int id;
    private String serviceCode;
    private String serviceName;
    private String serviceIcon;
    private Long serviceTariff;

    public ServicesEntity(String serviceCode, String serviceName, String serviceIcon, Long serviceTariff) {
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.serviceIcon = serviceIcon;
        this.serviceTariff = serviceTariff;
    }
}
