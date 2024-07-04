package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Manager;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.ManagerRepository;
import com.example.fresher_manager.service.ManagerService;
import com.example.fresher_manager.validator.ManagerValidator;
import com.example.fresher_manager.validator.impl.EmailValidator;
import com.example.fresher_manager.validator.impl.PhoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ManagerValidator managerValidator;

    @Override
    public boolean save(Manager manager) {
        managerValidator.validateUsername(manager.getUsername());
        managerValidator.validateEmail(manager.getEmail());
        managerValidator.validatePhone(manager.getPhone());

        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        managerRepository.save(manager);
        return true;
    }


    @Override
    public Manager findById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found with id: " + id));
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAllByStatusTrue();
    }



    @Override
    public Manager getActiveManagerByCenterId(Long centerId) {
        return managerRepository.findManagerByCenterIdAndEndDateIsNull(centerId);
    }

}
