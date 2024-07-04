package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Manager;

import java.util.List;

public interface ManagerService extends UserService<Manager>{
    Manager getActiveManagerByCenterId(Long centerId);
}
