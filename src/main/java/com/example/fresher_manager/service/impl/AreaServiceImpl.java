package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Area;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.IAreaRepository;
import com.example.fresher_manager.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final IAreaRepository areaRepository;

    @Override
    public Area getActiveById(Long id) {
        return areaRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Area not found with id: " + id));
    }
}
