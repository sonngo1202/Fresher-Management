package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.entity.Manager;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ManagerValidatorImpl implements ManagerValidator {
    private final IManagerRepository managerRepository;
    private final IUserRepository userRepository;
    private final PhoneValidator phoneValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validateEmail(String email) {
        emailValidator.validate(email);

        if(managerRepository.findByEmail(email) != null){
            log.error("Email is already taken!");
            throw new EmailAlreadyExistsException("Email is already taken!");
        }
    }

    @Override
    public void validatePhone(String phone) {
        phoneValidator.validate(phone);

        if(managerRepository.findByPhone(phone) != null){
            log.error("Phone is already taken!");
            throw new PhoneAlreadyExistsException("Phone is already taken!");
        }
    }

    @Override
    public void validateUsername(String username) {
        if(username == null || username.isEmpty()){
            log.error("Username cannot be null or empty");
            throw new ValidationException("Username cannot be null or empty");
        }

        if(userRepository.findByUsername(username) != null){
            log.error("Username is already taken!");
            throw new UsernameAlreadyExistsException("Username is already taken!");
        }
    }

    @Override
    public void validateCreate(Manager manager) {
        validateUsername(manager.getUsername());
        validateEmail(manager.getEmail());
        validatePhone(manager.getPhone());
    }
}
