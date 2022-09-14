package com.example.Hangman.service;

import com.example.Hangman.exception.AlreadyUsedLetterException;
import com.example.Hangman.model.Game;
import com.example.Hangman.model.TryResponse;
import com.example.Hangman.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Game createRoom(String hiddenWord) {
        int length = 4;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedKey = RandomStringUtils.random(length, useLetters, useNumbers);

        String shownWord = "";
        for (int i = 0; i < hiddenWord.length(); i++) {
            shownWord = shownWord.concat("*");
        }
        return gameRepository.save(new Game(null, generatedKey, 0, hiddenWord, shownWord, ""));
    }

    public TryResponse guessWord(String roomId, String guess) throws AlreadyUsedLetterException {
        Game game = gameRepository.findByRoomId(roomId);
        if (game.getUsedLetters().contains(guess)) {
            throw new AlreadyUsedLetterException();
        }
        if (game.getUsedLetters().length() == 0) {
            game.setUsedLetters(guess);
        } else
            game.setUsedLetters(game.getUsedLetters() + "+" + guess);
        List<String> shownWord = new ArrayList<>(); // word
        List<String> hiddenWord = new ArrayList<>(); // ****
        for (int i = 0; i < game.getWord().length(); i++) {
            shownWord.add(String.valueOf(game.getWord().charAt(i)));
            hiddenWord.add(String.valueOf(game.getHiddenWord().charAt(i)));
        }
        Boolean guessed = false;
        while (shownWord.contains(guess) && !hiddenWord.contains(guess)) {
            guessed = true;
            hiddenWord.set(shownWord.indexOf(guess), shownWord.get(shownWord.indexOf(guess)));
            shownWord.set(shownWord.indexOf(guess), "*");
        }

        if (!guessed) {
            game.setCounter(game.getCounter() + 1);
        }
        String tmpHiddenWord = "";
        for (int i = 0; i < game.getWord().length(); i++) {
            tmpHiddenWord = tmpHiddenWord.concat(hiddenWord.get(i));
        }
        game.setHiddenWord(tmpHiddenWord);

        return new TryResponse(guessed, game);
    }
}