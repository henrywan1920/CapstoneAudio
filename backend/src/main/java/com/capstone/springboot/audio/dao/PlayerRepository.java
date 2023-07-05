package com.capstone.springboot.audio.dao;

import com.capstone.springboot.audio.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findByUsernameAndPlaylistId(String username, int playlistId);
    List<Player> findByUsername(String username);
}
