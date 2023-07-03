package com.capstone.springboot.audio.config;

import com.amazonaws.auth.*;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.transcribe.AmazonTranscribeClient;
import com.amazonaws.services.transcribe.AmazonTranscribeClientBuilder;
import com.capstone.springboot.audio.models.AWSCredentialDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class AWSConfig {
    @Bean
    public AmazonS3Client amazonS3Client() {
        AWSCredentialDTO credential = getAWSCredential();
        AWSCredentials awsCredentials = new BasicAWSCredentials(
            credential.getAccessKey(),
            credential.getSecretKey()
        );
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .build();
    }

    @Bean
    public AmazonTranscribeClient amazonTranscribeClient() {
        AWSCredentialDTO credential = getAWSCredential();
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                credential.getAccessKey(),
                credential.getSecretKey()
        );
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        return (AmazonTranscribeClient) AmazonTranscribeClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .build();
    }

    private AWSCredentialDTO getAWSCredential() {
        ObjectMapper objectMapper = new ObjectMapper();
        AWSCredentialDTO awsCredentialDTO = null;
        try {
            awsCredentialDTO = objectMapper.readValue(loadAWSCredentialsWithSpringInternalClass(), AWSCredentialDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return awsCredentialDTO;
    }

    private File loadAWSCredentialsWithSpringInternalClass() {
        try {
            return ResourceUtils.getFile("classpath:AWSCredentials.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
