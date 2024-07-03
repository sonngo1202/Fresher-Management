package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Manager;

import java.util.List;

public interface ManagerService {
    boolean add(Manager manager);
    List<Manager> getAll();
    Manager get(Long id);
    boolean existsById(Long id);
    Manager getActiveManagerByCenterId(Long centerId);
}
