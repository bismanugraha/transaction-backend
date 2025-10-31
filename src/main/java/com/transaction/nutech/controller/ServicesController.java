package com.transaction.nutech.controller;

import com.transaction.nutech.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.transaction.nutech.constant.Paths.SERVICES;

@RestController
public class ServicesController {

    @Autowired
    ServicesService servicesService;

    @RequestMapping(
            path = SERVICES,
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllServices() {
        return servicesService.getAllServices();
    }
}
