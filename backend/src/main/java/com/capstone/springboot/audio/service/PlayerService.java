package com.capstone.springboot.audio.service;

import com.capstone.springboot.audio.entity.Player;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface PlayerService {
    HashMap<String, List<String>> getAudiosUploadedBy(String username);
    public void createNewRecordWith(String username,
                                    String audioName, String audioUrl,
                                    String transcriptName, String transcriptUrl,
                                    String playlist);
    public String getAudioObjectUrlWith(String username, String playlist, String audioFileName);
    public String putObject(File audioFile, String key);
    public void removeExistingTranscriptWith(String username, String playlist, String transcriptFileName);
    public String speechToTextFromAudio(String mediaUri, String jobName, String language, String outputKey);
}
