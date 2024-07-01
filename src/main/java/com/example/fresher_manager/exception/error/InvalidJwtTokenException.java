package com.example.fresher_manager.exception.error;

public class InvalidJwtTokenException extends RuntimeException{
    public InvalidJwtTokenException(String message){
        super(message);
    }
}
