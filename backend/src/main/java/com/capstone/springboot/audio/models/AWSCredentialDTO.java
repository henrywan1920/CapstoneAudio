package com.capstone.springboot.audio.models;

public class AWSCredentialDTO {
    private String accessKey;
    private String secretKey;

    public AWSCredentialDTO() {
    }

    public AWSCredentialDTO(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
