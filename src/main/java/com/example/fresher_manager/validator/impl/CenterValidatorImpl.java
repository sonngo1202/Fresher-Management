package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.exception.error.CenterNameAlreadyExistsException;
import com.example.fresher_manager.exception.error.EmailAlreadyExistsException;
import com.example.fresher_manager.exception.error.PhoneAlreadyExistsException;
import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.repository.CenterRepository;
import com.example.fresher_manager.validator.CenterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CenterValidatorImpl implements CenterValidator {

    private final CenterRepository centerRepository;
    private final PhoneValidator phoneValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validateName(String name) {
        if(name == null || name.isEmpty()){
            throw new ValidationException("Center name cannot be null or empty");
        }

        if(centerRepository.findByName(name) != null){
            throw new CenterNameAlreadyExistsException("Name is already taken!");
        }
    }

    @Override
    public void validateEmail(String email) {
        emailValidator.validate(email);

        if(centerRepository.findByEmail(email) != null){
            throw new EmailAlreadyExistsException("Email is already taken!");
        }
    }

    @Override
    public void validatePhone(String phone) {
        phoneValidator.validate(phone);

        if(centerRepository.findByPhone(phone) != null){
            throw new PhoneAlreadyExistsException("Phone is already taken!");
        }
    }
}
