package com.example.fresher_manager.exception.error;

public class FresherCodeAlreadyExistsException extends RuntimeException{
    public FresherCodeAlreadyExistsException(String message){
        super(message);
    }
}
