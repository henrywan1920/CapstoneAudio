package com.capstone.springboot.audio.models.response;

import java.util.HashMap;
import java.util.List;


public class FetchAudiosResponse extends BasicResponse {
    private HashMap<String, List<String>> audios;

    public FetchAudiosResponse() {
    }

    public FetchAudiosResponse(int status, String message, long timeStamp, HashMap<String, List<String>> audios) {
        super(status, message, timeStamp);
        this.audios = audios;
    }



    public HashMap<String, List<String>> getAudios() {
        return audios;
    }

    public void setAudios(HashMap<String, List<String>> audios) {
        this.audios = audios;
    }

    public List<String> getAudiosWith(String email, String playlist) {
        return this.audios.get(playlist);
    }

    public void addAudioTo(String playlist, String newAudioName) {
        List<String> audiosInAPlaylist = this.audios.get(playlist);
        audiosInAPlaylist.add(newAudioName);
    }
}
