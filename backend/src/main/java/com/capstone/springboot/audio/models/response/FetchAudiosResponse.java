package com.capstone.springboot.audio.models.response;

import java.util.HashMap;
import java.util.List;


public class FetchAudiosResponse {
    private HashMap<String, List<String>> audios;
    private String message;

    private int status;

    private long timeStamp;

    public FetchAudiosResponse(HashMap<String, List<String>> audios, String message) {
        this.audios = audios;
        this.message = message;
    }

    public HashMap<String, List<String>> getAudios() {
        return audios;
    }

    public void setAudios(HashMap<String, List<String>> audios) {
        this.audios = audios;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getAudiosWith(String email, String playlist) {
        return this.audios.get(playlist);
    }

    public void addAudioTo(String playlist, String newAudioName) {
        List<String> audiosInAPlaylist = this.audios.get(playlist);
        audiosInAPlaylist.add(newAudioName);
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
        return "FetchAudiosResponse{" +
                "audios=" + audios +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
