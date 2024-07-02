package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Area;
import com.example.fresher_manager.repository.AreaRepository;
import com.example.fresher_manager.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    @Override
    public Area get(Long id) {
        return areaRepository.getById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return areaRepository.existsByIdAndStatusTrue(id);
    }
}
