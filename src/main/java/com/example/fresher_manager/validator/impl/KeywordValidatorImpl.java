package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.validator.KeywordValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KeywordValidatorImpl implements KeywordValidator {
    @Override
    public void validate(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()){
            log.error("Keyword must not be empty");
            throw new ValidationException("Keyword must not be empty");
        }
    }
}
