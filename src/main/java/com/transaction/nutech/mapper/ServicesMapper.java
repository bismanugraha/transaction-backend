package com.transaction.nutech.mapper;

import com.transaction.nutech.model.entity.ServicesEntity;
import com.transaction.nutech.model.response.ServicesResponse;
import org.springframework.stereotype.Component;

@Component
public class ServicesMapper {
    public ServicesResponse mapResponse(ServicesEntity servicesEntity) {
        return new ServicesResponse(servicesEntity.getServiceCode(), servicesEntity.getServiceName(), servicesEntity.getServiceIcon(), servicesEntity.getServiceTariff());
    }
}
