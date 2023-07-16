package com.capstone.springboot.audio.rest;

import com.capstone.springboot.audio.models.request.UserLoginRequest;
import com.capstone.springboot.audio.models.request.UserRegisterRequest;
import com.capstone.springboot.audio.models.response.BasicResponse;
import com.capstone.springboot.audio.models.response.LoginResponse;
import com.capstone.springboot.audio.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<BasicResponse> addUser(@ModelAttribute UserRegisterRequest userRegisterRequest){
        BasicResponse response = new BasicResponse("Sign up successfully");
        String theUsername = userRegisterRequest.getUsername();
        String thePassword = userRegisterRequest.getPassword();
        String theRole = "temp";
        userService.createNewUser(theUsername, thePassword,theRole);
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> userLogin(@ModelAttribute UserLoginRequest userLoginRequest) {
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        String message = "";
        int status = 0;
        HttpStatusCode httpStatusCode = HttpStatus.OK;

        if (!userService.checkUserExistsBy(username)) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }

        if (userService.checkUsernamePassword(username, password)) {
            message = "Login successfully";
            status = HttpStatus.OK.value();
        }
        else {
            message = "Username and password not match";
            status = HttpStatus.UNAUTHORIZED.value();
            httpStatusCode = HttpStatus.UNAUTHORIZED;
        }

        LoginResponse response = new LoginResponse(message);
        response.setStatus(status);
        response.setTimeStamp(System.currentTimeMillis());
        response.setUsername(username);
        return new ResponseEntity<>(response, httpStatusCode);
    }

}

