package com.example.fresher_manager.validator;

public interface UserValidator {
    void validateUsername(String username);
    void validateEmail(String email);
    void validatePhone(String phone);
}
