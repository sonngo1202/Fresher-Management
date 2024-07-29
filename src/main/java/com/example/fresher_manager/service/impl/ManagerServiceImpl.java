package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Manager;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.IManagerRepository;
import com.example.fresher_manager.service.ManagerService;
import com.example.fresher_manager.validator.ManagerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final IManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ManagerValidator managerValidator;

    @Override
    public boolean save(Manager manager) {
        managerValidator.validateCreate(manager);

        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        managerRepository.save(manager);
        return true;
    }


    @Override
    public Manager getActiveUserById(Long id) {
        return managerRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> {
                    log.error("Manager not found with id: " + id);
                    return new ResourceNotFoundException("Manager not found with id: " + id);
                });
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAllByStatusTrue();
    }

    @Override
    public Manager getCurrentManagerByCenterId(Long centerId) {
        return managerRepository.findManagerByCenterIdAndEndDateIsNull(centerId);
    }
}
