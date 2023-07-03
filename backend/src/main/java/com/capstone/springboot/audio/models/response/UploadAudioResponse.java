package com.capstone.springboot.audio.models.response;

public class UploadAudioResponse {
    private String mediaFileName;
    private String transcriptFileName;
    private String message;

    public UploadAudioResponse() {
    }

    public UploadAudioResponse(String mediaFileName, String transcriptFileName, String message) {
        this.mediaFileName = mediaFileName;
        this.transcriptFileName = transcriptFileName;
        this.message = message;
    }

    public String getMediaFileName() {
        return mediaFileName;
    }

    public void setMediaFileName(String mediaFileName) {
        this.mediaFileName = mediaFileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTranscriptFileName() {
        return transcriptFileName;
    }

    public void setTranscriptFileName(String transcriptFileName) {
        this.transcriptFileName = transcriptFileName;
    }

    @Override
    public String toString() {
        return "UploadAudioResponse{" +
                "mediaFileName='" + mediaFileName + '\'' +
                ", transcriptFileName='" + transcriptFileName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
