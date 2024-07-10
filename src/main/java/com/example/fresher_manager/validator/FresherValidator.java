package com.example.fresher_manager.validator;

import com.example.fresher_manager.entity.Fresher;

public interface FresherValidator extends UserValidator{
    void validateCode(String code);
    void validateCreate(Fresher createFresher);
    void validateUpdate(Fresher updateFresher, Fresher currentFresher);
}
