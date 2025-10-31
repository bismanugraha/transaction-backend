package com.transaction.nutech.controller;

import com.transaction.nutech.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.transaction.nutech.constant.Paths.BANNER;

@RestController
public class BannerController {

    @Autowired
    BannerService bannerService;

    @RequestMapping(
            path = BANNER,
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllBanner() {
        return bannerService.getAllBanner();
    }
}
