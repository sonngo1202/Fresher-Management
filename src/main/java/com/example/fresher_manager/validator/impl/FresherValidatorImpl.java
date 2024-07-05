package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.exception.error.*;
import com.example.fresher_manager.repository.IFresherRepository;
import com.example.fresher_manager.repository.IUserRepository;
import com.example.fresher_manager.validator.EmailValidator;
import com.example.fresher_manager.validator.FresherValidator;
import com.example.fresher_manager.validator.PhoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FresherValidatorImpl implements FresherValidator {

    private final IFresherRepository fresherRepository;
    private final IUserRepository userRepository;
    private final PhoneValidator phoneValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validateUsername(String username) {
        if(username == null || username.isEmpty()){
            throw new ValidationException("Username cannot be null or empty");
        }

        if(userRepository.findByUsername(username) != null){
            throw new UsernameAlreadyExistsException("Username is already taken!");
        }
    }

    @Override
    public void validateCode(String code) {
        if(code == null || code.isEmpty()){
            throw new ValidationException("Fresher Code cannot be null or empty");
        }

        if(fresherRepository.findByCode(code) != null){
            throw new FresherCodeAlreadyExistsException("Code is already taken!");
        }
    }

    @Override
    public void validateEmail(String email) {
        emailValidator.validate(email);

        if(fresherRepository.findByEmail(email) != null){
            throw new EmailAlreadyExistsException("Email is already taken!");
        }
    }

    @Override
    public void validatePhone(String phone) {
        phoneValidator.validate(phone);

        if(fresherRepository.findByPhone(phone) != null){
            throw new PhoneAlreadyExistsException("Phone is already taken!");
        }
    }
}
