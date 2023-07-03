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

    @Column(name = "arn")
    private String arn;

    public Transcript(String name, String arn) {
        this.name = name;
        this.arn = arn;
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

    public String getArn() {
        return arn;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    @Override
    public String toString() {
        return "Transcript{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", arn='" + arn + '\'' +
                '}';
    }
}
