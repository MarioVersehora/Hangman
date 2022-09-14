package com.example.Hangman.exception;

public class AlreadyUsedLetterException extends Exception {
    public AlreadyUsedLetterException() {
        super("Letter already used!");
    }
}