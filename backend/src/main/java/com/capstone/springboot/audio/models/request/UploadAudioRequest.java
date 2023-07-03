package com.capstone.springboot.audio.models.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadAudioRequest {
    private String playlist;
    private String mediaFileName;

    private String transcriptFileName;
    private String language;
    private MultipartFile content;

    public UploadAudioRequest() {
    }

    public UploadAudioRequest(String playlist, String mediaFileName, String transcriptFileName, String language, MultipartFile content) {
        this.playlist = playlist;
        this.mediaFileName = mediaFileName;
        this.transcriptFileName = transcriptFileName;
        this.language = language;
        this.content = content;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public String getMediaFileName() {
        return mediaFileName;
    }

    public void setMediaFileName(String mediaFileName) {
        this.mediaFileName = mediaFileName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public MultipartFile getContent() {
        return content;
    }

    public void setContent(MultipartFile content) {
        this.content = content;
    }

    public String getTranscriptFileName() {
        return transcriptFileName;
    }

    public void setTranscriptFileName(String transcriptFileName) {
        this.transcriptFileName = transcriptFileName;
    }
}
