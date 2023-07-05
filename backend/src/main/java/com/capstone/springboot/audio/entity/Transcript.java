package com.capstone.springboot.audio.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transcript")
public class Transcript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    public Transcript() {
    }

    public Transcript(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Transcript{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", arn='" + url + '\'' +
                '}';
    }
}
