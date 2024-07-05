package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.exception.error.EmailAlreadyExistsException;
import com.example.fresher_manager.exception.error.PhoneAlreadyExistsException;
import com.example.fresher_manager.exception.error.UsernameAlreadyExistsException;
import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.repository.IManagerRepository;
import com.example.fresher_manager.repository.IUserRepository;
import com.example.fresher_manager.validator.EmailValidator;
import com.example.fresher_manager.validator.ManagerValidator;
import com.example.fresher_manager.validator.PhoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerValidatorImpl implements ManagerValidator {
    private final IManagerRepository managerRepository;
    private final IUserRepository userRepository;
    private final PhoneValidator phoneValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validateEmail(String email) {
        emailValidator.validate(email);

        if(managerRepository.findByEmail(email) != null){
            throw new EmailAlreadyExistsException("Email is already taken!");
        }
    }

    @Override
    public void validatePhone(String phone) {
        phoneValidator.validate(phone);

        if(managerRepository.findByPhone(phone) != null){
            throw new PhoneAlreadyExistsException("Phone is already taken!");
        }
    }

    @Override
    public void validateUsername(String username) {
        if(username == null || username.isEmpty()){
            throw new ValidationException("Username cannot be null or empty");
        }

        if(userRepository.findByUsername(username) != null){
            throw new UsernameAlreadyExistsException("Username is already taken!");
        }
    }
}
