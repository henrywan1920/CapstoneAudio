package com.capstone.springboot.audio.dummyrest;

import com.capstone.springboot.audio.models.response.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dummy/user")
public class DummyUserController {
    @PostMapping("/register")
    public ResponseEntity<BasicResponse> addUser(){
        BasicResponse response = new BasicResponse("Sign up successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<BasicResponse> userLogin() {
        BasicResponse response = new BasicResponse("Login successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
