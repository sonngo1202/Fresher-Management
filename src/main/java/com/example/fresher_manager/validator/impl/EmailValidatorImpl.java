package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.validator.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@Slf4j
public class EmailValidatorImpl implements EmailValidator {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    @Override
    public void validate(String email) {
        if (!pattern.matcher(email).matches()) {
            log.error("Email is not valid.");
            throw new ValidationException("Email is not valid.");
        }
    }
}
