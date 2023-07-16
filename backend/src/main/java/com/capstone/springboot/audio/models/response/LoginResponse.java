package com.capstone.springboot.audio.models.response;

public class LoginResponse extends BasicResponse {
    private String username;

    public LoginResponse() {
    }

    public LoginResponse(String message) {
        super(message);
    }

    public LoginResponse(int status, String message, long timeStamp, String username) {
        super(status, message, timeStamp);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
