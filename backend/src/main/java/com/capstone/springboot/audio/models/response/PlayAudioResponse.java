package com.capstone.springboot.audio.models.response;

public class PlayAudioResponse {

    private String audio;
    private String subtitle;
    private String message;
    private int status;
    private long timeStamp;

    public PlayAudioResponse(String audio, String subtitle, String message) {
        this.audio = audio;
        this.subtitle = subtitle;
        this.message = message;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        return "PlayAudioResponse{" +
                "audio='" + audio + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
