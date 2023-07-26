package com.capstone.springboot.audio.config;

import com.amazonaws.auth.*;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.transcribe.AmazonTranscribeClient;
import com.amazonaws.services.transcribe.AmazonTranscribeClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Bean
    public AmazonS3Client amazonS3Client() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
            this.accessKey,
            this.secretKey
        );
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .build();
    }

    @Bean
    public AmazonTranscribeClient amazonTranscribeClient() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                this.accessKey,
                this.secretKey
        );
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        return (AmazonTranscribeClient) AmazonTranscribeClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .build();
    }

    /*private AWSCredentialDTO getAWSCredential() {
        ObjectMapper objectMapper = new ObjectMapper();
        AWSCredentialDTO awsCredentialDTO = null;
        try {
            awsCredentialDTO = objectMapper.readValue(loadAWSCredentialsWithSpringInternalClass(), AWSCredentialDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return awsCredentialDTO;
    }*/

    /*private File loadAWSCredentialsWithSpringInternalClass() {
        try {
            return ResourceUtils.getFile("classpath:AWSCredentials.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/
}
