package com.capstone.springboot.audio.rest;

import com.capstone.springboot.audio.models.request.UserRegisterRequest;
import com.capstone.springboot.audio.models.response.BasicResponse;
import com.capstone.springboot.audio.models.response.ErrorResponse;
import com.capstone.springboot.audio.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<BasicResponse> addUser(@RequestBody UserRegisterRequest userRegisterRequest){
        BasicResponse response = new BasicResponse("Sign up successfully");
        String theUsername = userRegisterRequest.getUsername();
        String thePassword = userRegisterRequest.getPassword();
        String theRole = userRegisterRequest.getRole();
        userService.createNewUser(theUsername, thePassword,theRole);
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<BasicResponse> userLogin() {
        /*Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof User) {
            username = ((User)principal).getUsername();
        } else {
            username = principal.toString();
        }*/
        // boolean existing = userService.findUserByName(username);

        BasicResponse response = new BasicResponse("Login successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

