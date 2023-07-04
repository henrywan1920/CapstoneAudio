package com.capstone.springboot.audio.dao;

import com.capstone.springboot.audio.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
