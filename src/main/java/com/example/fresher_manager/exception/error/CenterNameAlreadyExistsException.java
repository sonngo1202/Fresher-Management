package com.example.fresher_manager.exception.error;

public class CenterNameAlreadyExistsException extends RuntimeException{
    public CenterNameAlreadyExistsException(String message){
        super(message);
    }
}
