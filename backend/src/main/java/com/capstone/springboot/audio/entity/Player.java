package com.capstone.springboot.audio.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // username must be an email
    @Column(name = "username")
    private String username;

    @Column(name = "audio_id")
    private int audioId;

    @Column(name = "transcript_id")
    private int transcriptId;

    @Column(name = "playlist_id")
    private int playlistId;

    public Player(String username, int audioId, int transcriptId, int playlistId) {
        this.username = username;
        this.audioId = audioId;
        this.transcriptId = transcriptId;
        this.playlistId = playlistId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public int getTranscriptId() {
        return transcriptId;
    }

    public void setTranscriptId(int transcriptId) {
        this.transcriptId = transcriptId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", audioId=" + audioId +
                ", transcriptId=" + transcriptId +
                ", playlistId=" + playlistId +
                '}';
    }
}
