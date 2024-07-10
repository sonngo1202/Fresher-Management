package com.example.fresher_manager.validator;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.Center;

public interface CenterValidator {
    void validateName(String name);
    void validateEmail(String email);
    void validatePhone(String phone);
    void validateCreate(CenterRequest createRequest);
    void validateUpdate(CenterRequest updateCenter, Center currentCenter);
}
