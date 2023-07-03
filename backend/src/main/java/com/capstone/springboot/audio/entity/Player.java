package com.capstone.springboot.audio.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;

    @Column(name = "audio_id")
    private int audioId;

    @Column(name = "transcript_id")
    private int transcriptId;

    @Column(name = "playlist_id")
    private int playlistId;

    public Player(int userId, int audioId, int transcriptId, int playlistId) {
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", audioId=" + audioId +
                ", transcriptId=" + transcriptId +
                ", playlistId=" + playlistId +
                '}';
    }
}
