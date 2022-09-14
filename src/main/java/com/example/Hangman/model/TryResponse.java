package com.example.Hangman.model;

public class TryResponse {
    GameState gameState;
    Boolean guessed;
    Integer triesLeft;

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
    }
}