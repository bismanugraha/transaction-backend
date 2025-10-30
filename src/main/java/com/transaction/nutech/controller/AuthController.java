package com.transaction.nutech.controller;

import com.transaction.nutech.model.request.UserRequest;
import com.transaction.nutech.model.response.GenericResponse;
import com.transaction.nutech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> profile(@RequestBody UserRequest user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            path = PROFILE_UPDATE,
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> profileUpdate(@RequestBody UserRequest user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            path = IMAGE_UPDATE,
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> imageUpdate(@RequestBody UserRequest user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
