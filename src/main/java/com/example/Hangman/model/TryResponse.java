package com.example.Hangman.model;

import java.util.Arrays;
import java.util.List;

public class TryResponse {
    GameState gameState;
    Boolean guessed;
    Integer triesLeft;

    List<String> lettersTried;

    public TryResponse(Boolean guessed, Game game) {
        this.guessed = guessed;
        this.triesLeft = 10 - game.counter;

        if(!game.hiddenWord.contains("*") && this.triesLeft > 0) {
            this.gameState = GameState.WON;
        } else if (game.hiddenWord.contains("*") && this.triesLeft <= 0){
            this.gameState = GameState.LOST;
        } else {
            this.gameState = GameState.IN_PROGRESS;
        }

        this.lettersTried = Arrays.asList(game.getUsedLetters().split("\\+"));
    }
}