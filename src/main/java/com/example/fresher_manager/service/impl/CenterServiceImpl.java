package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.*;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.CenterRepository;
import com.example.fresher_manager.service.*;
import com.example.fresher_manager.validator.CenterValidator;
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
    private final CourseService courseService;
    private final FresherService fresherService;
    private final EnrollmentService enrollmentService;

    private final CenterValidator centerValidator;

    @Override
    public List<Center> getAll() {
        return centerRepository.findAll();
    }

    @Override
    @Transactional
    public boolean create(CenterRequest centerRequest) {
        centerValidator.validateName(centerRequest.getName());
        centerValidator.validateEmail(centerRequest.getEmail());
        centerValidator.validatePhone(centerRequest.getPhone());

        Center center = new Center();
        center.setName(centerRequest.getName());
        center.setAddress(centerRequest.getAddress());
        center.setEmail(centerRequest.getEmail());
        center.setPhone(centerRequest.getPhone());
        center.setArea(areaService.get(centerRequest.getAreaId()));

        Center newCenter = centerRepository.save(center);

        Management management = new Management();
        management.setCenter(newCenter);
        management.setManager(managerService.findById(centerRequest.getManagerId()));

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

        if(!centerRequest.getName().equalsIgnoreCase(center.getName())){
            centerValidator.validateName(centerRequest.getName());
        }

        if(!centerRequest.getEmail().equalsIgnoreCase(center.getEmail())){
            centerValidator.validateEmail(centerRequest.getEmail());
        }

        if(!centerRequest.getPhone().equalsIgnoreCase(center.getPhone())){
            centerValidator.validatePhone(centerRequest.getPhone());
        }

        center.setName(centerRequest.getName());
        center.setAddress(centerRequest.getAddress());
        center.setEmail(centerRequest.getEmail());
        center.setPhone(centerRequest.getPhone());
        center.setArea(areaService.get(centerRequest.getAreaId()));

        centerRepository.save(center);

        Manager manager = managerService.getActiveManagerByCenterId(center.getId());
        if(!manager.getId().equals(centerRequest.getManagerId())){
            managementService.update(center.getId(), manager.getId());

            Management newManagement = new Management();
            newManagement.setCenter(center);
            newManagement.setManager(managerService.findById(centerRequest.getManagerId()));

            managementService.save(newManagement);
        }

        return true;
    }

    @Override
    public boolean addCourse(Long id, Course course) {
        Center center = findCenterById(id);
        course.setCenter(center);
        courseService.save(course);
        return true;
    }

    @Override
    public boolean assignFresherToCenter(Long fresherId, Long courseId) {
        Fresher fresher = fresherService.findById(fresherId);
        Course course = courseService.findById(courseId);

        Enrollment enrollment = new Enrollment();
        enrollment.setFresher(fresher);
        enrollment.setCourse(course);
        enrollment.setStartDate(course.getStartDate());
        enrollment.setEndDate(course.getEndDate());

        enrollmentService.save(enrollment);

        return true;
    }

    @Override
    public boolean merge() {
        return false;
    }

    @Override
    public Center findCenterById(Long id) {
        return centerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found with id: " + id));
    }

}
