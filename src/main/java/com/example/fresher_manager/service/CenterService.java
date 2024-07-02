package com.example.fresher_manager.service;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.Center;

import java.util.List;

public interface CenterService {
    List<Center> getAll();
    boolean create(CenterRequest centerRequest);
    boolean delete(Long id);
    boolean update(Long id, CenterRequest centerRequest);
    boolean changeManager(Long id, Long manager_id);
    boolean assignFresherToCenter(Long fresher_id, Long id);
    boolean merge();
    void validateCenter(CenterRequest centerRequest);
    Center findCenterById(Long id);
}
