package com.example.fresher_manager.validator;

import com.example.fresher_manager.entity.Manager;

public interface ManagerValidator extends UserValidator{
    void validateCreate(Manager manager);
}
