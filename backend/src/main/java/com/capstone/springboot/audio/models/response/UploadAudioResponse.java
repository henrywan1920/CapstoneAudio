package com.capstone.springboot.audio.models.response;

public class UploadAudioResponse extends BasicResponse {
    private String mediaFileName;
    private String transcriptFileName;

    public UploadAudioResponse() {
    }

    public UploadAudioResponse(String message, String mediaFileName, String transcriptFileName) {
        super(message);
        this.mediaFileName = mediaFileName;
        this.transcriptFileName = transcriptFileName;
    }

    public UploadAudioResponse(int status, String message, long timeStamp, String mediaFileName, String transcriptFileName) {
        super(status, message, timeStamp);
        this.mediaFileName = mediaFileName;
        this.transcriptFileName = transcriptFileName;
    }

    public String getMediaFileName() {
        return mediaFileName;
    }

    public void setMediaFileName(String mediaFileName) {
        this.mediaFileName = mediaFileName;
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
                ", message='" + super.getMessage() + '\'' +
                ", status=" + super.getMessage() +
                ", timeStamp=" + super.getTimeStamp() +
                '}';
    }
}
