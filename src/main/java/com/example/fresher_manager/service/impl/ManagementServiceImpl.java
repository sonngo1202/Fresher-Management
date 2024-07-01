package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Management;
import com.example.fresher_manager.repository.ManagementRepository;
import com.example.fresher_manager.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService {

    private final ManagementRepository managementRepository;

    @Override
    public boolean save(Management management) {
        managementRepository.save(management);
        return true;
    }
}
