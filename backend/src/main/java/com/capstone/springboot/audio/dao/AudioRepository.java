package com.capstone.springboot.audio.dao;

import com.capstone.springboot.audio.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<Audio, Integer> {

}
