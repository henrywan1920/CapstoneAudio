package com.capstone.springboot.audio.models.response;

import java.util.HashMap;
import java.util.List;


public class FetchAudiosResponse {
    private HashMap<String, List<String>> audios;
    private String message;

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

    @Override
    public String toString() {
        return "FetchAudiosResponse{" +
                "audios=" + audios.toString() +
                ", message='" + message + '\'' +
                '}';
    }
}
