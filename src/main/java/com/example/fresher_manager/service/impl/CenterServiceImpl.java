package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.*;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.repository.ICenterRepository;
import com.example.fresher_manager.service.*;
import com.example.fresher_manager.validator.CenterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final ICenterRepository centerRepository;
    private final AreaService areaService;
    private final ManagerService managerService;
    private final ManagementService managementService;
    private final CourseService courseService;
    private final FresherService fresherService;
    private final EnrollmentService enrollmentService;

    private final CenterValidator centerValidator;

    @Override
    public List<Center> getAll() {
        return centerRepository.findByStatusTrue();
    }

    @Override
    @Transactional
    public Center create(CenterRequest centerRequest) {
        centerValidator.validateName(centerRequest.getName());
        centerValidator.validateEmail(centerRequest.getEmail());
        centerValidator.validatePhone(centerRequest.getPhone());

        Area area = areaService.getActiveById(centerRequest.getAreaId());
        Manager manager = managerService.getActiveUserById(centerRequest.getManagerId());

        Center center = new Center();
        center.setName(centerRequest.getName());
        center.setAddress(centerRequest.getAddress());
        center.setEmail(centerRequest.getEmail());
        center.setPhone(centerRequest.getPhone());
        center.setArea(area);

        Center newCenter = centerRepository.save(center);

        Management management = new Management();
        management.setCenter(newCenter);
        management.setManager(manager);

        managementService.save(management);

        return newCenter;
    }

    @Override
    public boolean deleteById(Long id) {
        Center center = getActiveCenterById(id);
        center.setStatus(false);
        centerRepository.save(center);
        return true;
    }

    @Override
    @Transactional
    public boolean updateById(Long id, CenterRequest centerRequest) {
        Center center = getActiveCenterById(id);

        if(!centerRequest.getName().equalsIgnoreCase(center.getName())){
            centerValidator.validateName(centerRequest.getName());
        }

        if(!centerRequest.getEmail().equalsIgnoreCase(center.getEmail())){
            centerValidator.validateEmail(centerRequest.getEmail());
        }

        if(!centerRequest.getPhone().equalsIgnoreCase(center.getPhone())){
            centerValidator.validatePhone(centerRequest.getPhone());
        }

        Area area = areaService.getActiveById(centerRequest.getAreaId());
        changeManager(center, centerRequest.getManagerId());

        center.setName(centerRequest.getName());
        center.setAddress(centerRequest.getAddress());
        center.setEmail(centerRequest.getEmail());
        center.setPhone(centerRequest.getPhone());
        center.setArea(area);

        centerRepository.save(center);

        return true;
    }

    @Override
    public boolean addCourseById(Long id, Course course) {
        Center center = getActiveCenterById(id);
        course.setCenter(center);
        courseService.save(course);
        return true;
    }

    @Override
    public boolean assignFresherToCenter(Long id, Long fresherId, Long courseId) {
        Center center = getActiveCenterById(id);
        Fresher fresher = fresherService.getActiveUserById(fresherId);
        Course course = courseService.getActiveCourseById(courseId);

        if(!courseService.isCourseBelongToCenter(courseId, id)){
            throw new ValidationException("Course with ID " + courseId + " does not belong to Center with ID " + id);
        }

        if(enrollmentService.isFresherEnrolledInCourse(fresherId, courseId)){
            throw new ValidationException("Fresher with ID " + fresherId + " is enrolled in Course with ID " + courseId);
        }

        enrollmentService.updateEndDateByFresherId(fresherId);

        Enrollment enrollment = new Enrollment();
        enrollment.setFresher(fresher);
        enrollment.setCourse(course);
        enrollment.setStartDate(course.getStartDate());

        enrollmentService.save(enrollment);

        return true;
    }

    @Override
    @Transactional
    public boolean merge(Long idCenter1, Long idCenter2, CenterRequest newCenterInfo) {
        Center center1 = getActiveCenterById(idCenter1);
        Center center2 = getActiveCenterById(idCenter2);

        Center newCenter;
        if(newCenterInfo.getId() == null){
            newCenter = create(newCenterInfo);
            courseService.updateCenterIdForActiveCourse(center1.getId(), newCenter.getId());
            deleteById(center1.getId());
            courseService.updateCenterIdForActiveCourse(center2.getId(), newCenter.getId());
            deleteById(center2.getId());
        }else{
            Long idNewCenter = newCenterInfo.getId();
            if(!idNewCenter.equals(idCenter1) && !idNewCenter.equals(idCenter2)){
                throw new ValidationException("");
            }else if(idNewCenter.equals(idCenter1)){
                newCenter = center1;
                courseService.updateCenterIdForActiveCourse(center2.getId(), newCenter.getId());
                deleteById(center2.getId());
                changeManager(newCenter, newCenterInfo.getManagerId());
            }else{
                newCenter = center2;
                courseService.updateCenterIdForActiveCourse(center1.getId(), newCenter.getId());
                deleteById(center1.getId());
                changeManager(newCenter, newCenterInfo.getManagerId());
            }
        }

        return true;
    }

    @Override
    public Center getActiveCenterById(Long id) {
        return centerRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found with id: " + id));
    }

    @Override
    public boolean changeManager(Center center, Long mangerId) {
        Manager manager = managerService.getCurrentManagerByCenterId(center.getId());
        if(!manager.getId().equals(mangerId)){
            Manager newManager = managerService.getActiveUserById(mangerId);
            Management management = new Management();
            management.setCenter(center);
            management.setManager(newManager);
            management.setStartDate(new Date());
            managementService.updateEndDateByCenterIdAndManagerId(center.getId(), mangerId);
            managementService.save(management);
        }
        return true;
    }

}
