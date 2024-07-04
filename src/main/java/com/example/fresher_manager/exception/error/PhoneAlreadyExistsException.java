package com.example.fresher_manager.exception.error;

public class PhoneAlreadyExistsException extends RuntimeException{
    public PhoneAlreadyExistsException(String message){
        super(message);
    }
}
