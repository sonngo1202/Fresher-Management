package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.entity.Management;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.CenterRepository;
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
        Center center = findCenterById(id);
        center.setStatus(false);
        centerRepository.save(center);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Long id, CenterRequest centerRequest) {
        Center center = findCenterById(id);

        validateCenter(centerRequest);

        center.setName(centerRequest.getName());
        center.setAddress(centerRequest.getAddress());
        center.setEmail(centerRequest.getEmail());
        center.setPhone(centerRequest.getPhone());
        center.setArea(areaService.get(centerRequest.getAreaId()));

        centerRepository.save(center);

        return true;
    }

    @Override
    @Transactional
    public boolean changeManager(Long id, Long manager_id) {
        Center center = findCenterById(id);

        if (!managerService.existsById(manager_id)) {
            throw new ResourceNotFoundException("Manager not found with id: " + manager_id);
        }

        Management management = new Management();
        management.setCenter(center);
        management.setManager(managerService.get(manager_id));

        managementService.save(management);

        return true;
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

    @Override
    public Center findCenterById(Long id) {
        return centerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found with id: " + id));
    }
}
