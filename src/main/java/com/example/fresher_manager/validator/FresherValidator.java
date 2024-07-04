package com.example.fresher_manager.validator;

public interface FresherValidator extends UserValidator{
    void validateCode(String code);
}
