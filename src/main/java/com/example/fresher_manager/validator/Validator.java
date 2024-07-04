package com.example.fresher_manager.validator;

import javax.xml.bind.ValidationException;

public interface Validator<T> {
    void validate(T entity);
}
