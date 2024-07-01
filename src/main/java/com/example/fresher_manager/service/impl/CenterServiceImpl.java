package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.entity.Management;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.CenterRepository;
import com.example.fresher_manager.repository.ManagementRepository;
import com.example.fresher_manager.service.AreaService;
import com.example.fresher_manager.service.CenterService;
import com.example.fresher_manager.service.ManagementService;
import com.example.fresher_manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;
    private final AreaService areaService;
    private final ManagerService managerService;
    private final ManagementService managementService;

    @Override
    public List<Center> getAll() {
        return centerRepository.findAll();
    }

    @Override
    @Transactional
    public boolean create(CenterRequest centerRequest) {
        validateCenter(centerRequest);

        Center center = new Center();
        center.setName(centerRequest.getName());
        center.setAddress(centerRequest.getAddress());
        center.setEmail(centerRequest.getEmail());
        center.setPhone(centerRequest.getPhone());
        center.setArea(areaService.get(centerRequest.getAreaId()));

        Center newCenter = centerRepository.save(center);

        Management management = new Management();
        management.setCenter(newCenter);
        management.setManager(managerService.get(centerRequest.getManagerId()));

        managementService.save(management);

        return true;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(CenterRequest centerRequest) {
        return false;
    }

    @Override
    public boolean assignFresherToCenter(Long fresher_id, Long id) {
        return false;
    }

    @Override
    public boolean merge() {
        return false;
    }

    @Override
    public void validateCenter(CenterRequest centerRequest) {
        if (!areaService.existsById(centerRequest.getAreaId())) {
            throw new ResourceNotFoundException("Area not found with id: " + centerRequest.getAreaId());
        }

        if (!managerService.existsById(centerRequest.getManagerId())) {
            throw new ResourceNotFoundException("Manager not found with id: " + centerRequest.getManagerId());
        }
    }
}
