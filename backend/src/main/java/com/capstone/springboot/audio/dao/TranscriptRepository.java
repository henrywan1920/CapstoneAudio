package com.capstone.springboot.audio.dao;

import com.capstone.springboot.audio.entity.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscriptRepository extends JpaRepository<Transcript, Integer> {

}
