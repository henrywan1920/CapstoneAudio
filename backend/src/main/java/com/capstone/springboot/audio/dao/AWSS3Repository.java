package com.capstone.springboot.audio.dao;

import java.io.File;

public interface AWSS3Repository {
    public String getURL(String bucketName, String key);
    public void putObject(String bucketName, String key, File file);
    public void deleteObject(String bucketName, String key);
    public boolean doesObjectExists(String bucketName, String key);
}
