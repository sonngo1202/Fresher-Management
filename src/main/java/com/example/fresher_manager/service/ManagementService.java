package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Management;

public interface ManagementService {
    boolean save(Management management);
    boolean update(Long centerId, Long managerId);
}
