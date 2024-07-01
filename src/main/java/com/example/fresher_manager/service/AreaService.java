package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Area;

public interface AreaService {
    Area get(Long id);
    boolean existsById(Long id);
}
