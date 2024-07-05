package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.validator.PhoneValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneValidatorImpl implements PhoneValidator {

    private static final String PHONE_REGEX = "^\\d{9,}$";

    private static final Pattern pattern = Pattern.compile(PHONE_REGEX);

    @Override
    public void validate(String phone) {
        if (!pattern.matcher(phone).matches()) {
            throw new ValidationException("Phone number is not valid.");
        }


    }
}
