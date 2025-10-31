package com.transaction.nutech.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class ImageValidator {
    public void validate(MultipartFile image) throws Exception {
        if(image.isEmpty()) {
            throw new Exception("Image tidak ditemukan!");
        }
        if(!List.of("image/jpeg", "image/png").contains(image.getContentType())) {
            throw new Exception("Format Image tidak sesuai!");
        }
    }
}
