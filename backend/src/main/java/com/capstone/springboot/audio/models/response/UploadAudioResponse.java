package com.capstone.springboot.audio.models.response;

public class UploadAudioResponse {
    private String mediaFileName;
    private String transcriptFileName;
    private String message;
    private int status;
    private long timeStamp;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "UploadAudioResponse{" +
                "mediaFileName='" + mediaFileName + '\'' +
                ", transcriptFileName='" + transcriptFileName + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
