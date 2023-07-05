package com.capstone.springboot.audio.dao;

import com.capstone.springboot.audio.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    List<Playlist> findPlaylistByName(String name);
}
