package com.capstone.springboot.audio.models.response;

public class PlayAudioResponse {

    private String audio;
    private String subtitle;
    private String message;

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
}
