package com.capstone.springboot.audio.dao;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class AWSS3Repository {
    private final AmazonS3Client s3Client;

    public AWSS3Repository(AmazonS3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String getURL(String bucketName, String key) {
        String objectURL = s3Client.getUrl(bucketName, key).toString();
        return objectURL;
    }

    public void putObject(String bucketName, String key, File file) {
        s3Client.putObject(bucketName, key, file);
        s3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
    }
}
