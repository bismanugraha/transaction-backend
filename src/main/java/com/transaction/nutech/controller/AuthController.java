package com.transaction.nutech.controller;

import com.transaction.nutech.model.request.UserRequest;
import com.transaction.nutech.model.response.GenericResponse;
import com.transaction.nutech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.transaction.nutech.constant.Paths.*;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping(
            path = REGISTER,
            method = RequestMethod.POST
    )
    public ResponseEntity<GenericResponse<Void>> register(@RequestBody UserRequest user) {
        return userService.registerUser(user);
    }

    @RequestMapping(
            path = LOGIN,
            method = RequestMethod.POST
    )
    public ResponseEntity<?> login(@RequestBody UserRequest user) {
        return userService.loginUser(user);
    }

    @RequestMapping(
            path = PROFILE,
            method = RequestMethod.GET
    )
    public ResponseEntity<?> profile(@RequestHeader(name = "Authorization") String authorization) throws Exception {
        return userService.getProfile(authorization);
    }

    @RequestMapping(
            path = PROFILE_UPDATE,
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> profileUpdate(
            @RequestHeader(name = "Authorization") String authorization,
            @RequestBody UserRequest user
    ) {
        return userService.updateProfile(authorization, user);
    }

    @RequestMapping(
            path = IMAGE_UPDATE,
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> imageUpdate(
            @RequestHeader(name = "Authorization") String authorization,
            @RequestParam MultipartFile image
    ) throws Exception {
        return userService.updateImageProfile(authorization, image);
    }
}
