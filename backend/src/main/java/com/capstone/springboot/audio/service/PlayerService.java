package com.capstone.springboot.audio.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface PlayerService {
    public void createNewRecordWith(String username,
                                    String audioName, String audioUrl,
                                    String transcriptName, String transcriptUrl,
                                    String playlist);
    public String putObject(File audioFile, String key);
    public String speechToTextFromAudio(String mediaUri, String jobName, String language, String outputKey);
}
