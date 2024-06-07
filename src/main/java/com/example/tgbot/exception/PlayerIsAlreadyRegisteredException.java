package com.example.tgbot.exception;

public class PlayerIsAlreadyRegisteredException extends RuntimeException{
    public PlayerIsAlreadyRegisteredException(String message) {
        super(message);
    }

}
