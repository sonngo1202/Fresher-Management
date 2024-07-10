package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.validator.KeywordValidator;
import org.springframework.stereotype.Component;

@Component
public class KeywordValidatorImpl implements KeywordValidator {
    @Override
    public void validate(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()){
            throw new ValidationException("Keyword must not be empty");
        }
    }
}
