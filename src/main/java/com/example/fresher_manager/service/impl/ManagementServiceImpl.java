package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Management;
import com.example.fresher_manager.repository.IManagementRepository;
import com.example.fresher_manager.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService {

    private final IManagementRepository managementRepository;

    @Override
    public boolean save(Management management) {
        managementRepository.save(management);
        return true;
    }

    @Override
    public boolean updateEndDateByCenterIdAndManagerId(Long centerId, Long managerId) {
        managementRepository.updateEndDateByCenterIdAndManagerId(centerId, managerId);
        return true;
    }

}
