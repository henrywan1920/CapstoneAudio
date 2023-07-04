package com.capstone.springboot.audio.dao;

import com.capstone.springboot.audio.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

}
