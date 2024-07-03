package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Manager;
import com.example.fresher_manager.repository.ManagerRepository;
import com.example.fresher_manager.repository.UserRepository;
import com.example.fresher_manager.service.AuthService;
import com.example.fresher_manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final ManagerRepository managerRepository;

    @Override
    public boolean add(Manager manager) {
        authService.validateUser(manager);
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        userRepository.save(manager);
        return true;
    }

    @Override
    public List<Manager> getAll() {
        return managerRepository.findAllByStatusTrue();
    }

    @Override
    public Manager get(Long id) {
        return managerRepository.getById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return managerRepository.existsByIdAndStatusTrue(id);
    }

    @Override
    public Manager getActiveManagerByCenterId(Long centerId) {
        return managerRepository.findManagerByCenterIdAndEndDateIsNull(centerId);
    }

}
