package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.validator.Validator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneValidator implements Validator<String> {

    private static final String PHONE_REGEX = "^\\d{9,}$";

    private static final Pattern pattern = Pattern.compile(PHONE_REGEX);

    @Override
    public void validate(String entity) {
        if (!pattern.matcher(entity).matches()) {
            throw new ValidationException("Phone number is not valid.");
        }


    }
}
