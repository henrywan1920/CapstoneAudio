package com.capstone.springboot.audio.models.response;

public class PlayAudioResponse extends BasicResponse {

    private String audio;
    private String subtitle;

    public PlayAudioResponse() {
    }

    public PlayAudioResponse(String message, String audio, String subtitle) {
        super(message);
        this.audio = audio;
        this.subtitle = subtitle;
    }

    public PlayAudioResponse(int status, String message, long timeStamp, String audio, String subtitle) {
        super(status, message, timeStamp);
        this.audio = audio;
        this.subtitle = subtitle;
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


    @Override
    public String toString() {
        return "PlayAudioResponse{" +
                "audio='" + audio + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", message='" + super.getMessage() + '\'' +
                ", status=" + super.getStatus() +
                ", timeStamp=" + super.getTimeStamp() +
                '}';
    }
}
