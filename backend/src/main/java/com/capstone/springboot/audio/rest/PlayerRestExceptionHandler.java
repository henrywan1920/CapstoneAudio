package com.capstone.springboot.audio.rest;

import com.capstone.springboot.audio.exception.AuthorizationException;
import com.capstone.springboot.audio.models.response.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlayerRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BasicResponse> handleException(AuthorizationException exception) {
        BasicResponse error = new BasicResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponse> handleException(Exception exception) {
        BasicResponse error = new BasicResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
