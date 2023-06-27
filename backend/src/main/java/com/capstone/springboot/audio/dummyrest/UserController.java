package com.capstone.springboot.audio.dummyrest;

import com.capstone.springboot.audio.models.response.BasicResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dummy/user")
public class UserController {
    @PostMapping("/register")
    public BasicResponse addUser(){
        BasicResponse response = new BasicResponse("Sign up successfully");
        return response;
    }

    @PostMapping("/login")
    public BasicResponse userLogin() {
        BasicResponse response = new BasicResponse("Login successfully");
        return response;
    }

}
