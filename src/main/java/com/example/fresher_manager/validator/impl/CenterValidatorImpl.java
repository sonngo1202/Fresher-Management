package com.example.fresher_manager.validator.impl;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.exception.error.CenterNameAlreadyExistsException;
import com.example.fresher_manager.exception.error.EmailAlreadyExistsException;
import com.example.fresher_manager.exception.error.PhoneAlreadyExistsException;
import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.repository.ICenterRepository;
import com.example.fresher_manager.validator.CenterValidator;
import com.example.fresher_manager.validator.EmailValidator;
import com.example.fresher_manager.validator.PhoneValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CenterValidatorImpl implements CenterValidator {

    private final ICenterRepository centerRepository;
    private final PhoneValidator phoneValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validateName(String name) {
        if(name == null || name.isEmpty()){
            log.error("Center name cannot be null or empty");
            throw new ValidationException("Center name cannot be null or empty");
        }

        if(centerRepository.findByName(name) != null){
            log.info("Name is already taken!");
            throw new CenterNameAlreadyExistsException("Name is already taken!");
        }
    }

    @Override
    public void validateEmail(String email) {
        emailValidator.validate(email);

        if(centerRepository.findByEmail(email) != null){
            log.info("Email is already taken!");
            throw new EmailAlreadyExistsException("Email is already taken!");
        }
    }

    @Override
    public void validatePhone(String phone) {
        phoneValidator.validate(phone);

        if(centerRepository.findByPhone(phone) != null){
            log.info("Phone is already taken!");
            throw new PhoneAlreadyExistsException("Phone is already taken!");
        }
    }

    @Override
    public void validateCreate(CenterRequest createCenter) {
        validateName(createCenter.getName());
        validateEmail(createCenter.getEmail());
        validatePhone(createCenter.getPhone());
    }

    @Override
    public void validateUpdate(CenterRequest updateCenter, Center currentCenter) {
        if(!updateCenter.getName().equalsIgnoreCase(currentCenter.getName())){
            validateName(updateCenter.getName());
        }

        if(!updateCenter.getEmail().equalsIgnoreCase(currentCenter.getEmail())){
            validateEmail(updateCenter.getEmail());
        }

        if(!updateCenter.getPhone().equalsIgnoreCase(currentCenter.getPhone())){
            validatePhone(updateCenter.getPhone());
        }
    }
}
