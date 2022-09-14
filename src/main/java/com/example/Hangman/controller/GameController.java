package com.example.Hangman.controller;

import com.example.Hangman.exception.AlreadyUsedLetterException;
import com.example.Hangman.model.Game;
import com.example.Hangman.model.TryResponse;
import com.example.Hangman.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("games")
public class GameController {
    private final GameService gameService;

    @PostMapping("{hiddenWord}")
    public Game createRoom(@PathVariable String hiddenWord) {
        return gameService.createRoom(hiddenWord);
    }

    @PostMapping("{roomId}/{guess}")
    public TryResponse guessWord(@PathVariable String roomId, @PathVariable String guess) throws AlreadyUsedLetterException {
        return gameService.guessWord(roomId, guess);
    }
}