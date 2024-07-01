package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.User;
import com.example.fresher_manager.exception.error.EmailAlreadyExistsException;
import com.example.fresher_manager.exception.error.UsernameAlreadyExistsException;
import com.example.fresher_manager.repository.UserRepository;
import com.example.fresher_manager.service.ValidateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateUserServiceImpl implements ValidateUserService {

    private final UserRepository userRepository;

    @Override
    public void validateUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new UsernameAlreadyExistsException("Username is already taken!");
        }
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new EmailAlreadyExistsException("Email is already taken!");
        }
    }
}
