package com.capstone.springboot.audio.models.request;

public class UploadAudioRequest {
    private String playlist;
    private String fileName;
    private String language;
    private String content;

    public UploadAudioRequest(String playlist, String fileName, String language, String content) {
        this.playlist = playlist;
        this.fileName = fileName;
        this.language = language;
        this.content = content;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
