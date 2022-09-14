package com.example.Hangman.repository;

import com.example.Hangman.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findByRoomId(String roomId);
}
