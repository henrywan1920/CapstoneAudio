package com.capstone.springboot.audio.dummyrest;

import com.capstone.springboot.audio.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dummy/user")
public class UserController {
    @PostMapping("/register")
    public SimpleResponse addUser(@RequestBody User theUser){
        SimpleResponse response = new SimpleResponse("Sign up successfully");
        return response;
    }

    @PostMapping("/login")
    public SimpleResponse userLogin() {
        SimpleResponse response = new SimpleResponse("Login successfully");
        return response;
    }

}
