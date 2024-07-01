package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Center;

import java.util.List;

public interface CenterService {
    List<Center> getAll();
    boolean create(Center center);
    boolean delete(Long id);
    boolean update(Center center);
    boolean assignFresherToCenter(Long fresher_id, Long id);
}
