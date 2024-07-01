package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.service.CenterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CenterServiceImpl implements CenterService {
    @Override
    public List<Center> getAll() {
        return List.of();
    }

    @Override
    public boolean create(Center center) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(Center center) {
        return false;
    }

    @Override
    public boolean assignFresherToCenter(Long fresher_id, Long id) {
        return false;
    }
}
