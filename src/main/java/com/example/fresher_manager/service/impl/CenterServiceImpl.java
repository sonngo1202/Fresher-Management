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
    private final HistoryService historyService;

    private final CenterValidator centerValidator;

    @Override
    public List<Center> getAll() {
        return centerRepository.findByStatusTrue();
    }

    @Override
    @Transactional
    public Center create(CenterRequest centerRequest) {
        centerValidator.validateCreate(centerRequest);

        Area area = areaService.getActiveById(centerRequest.getAreaId());
        Manager manager = managerService.getActiveUserById(centerRequest.getManagerId());

        Center center = new Center();
        center.setName(centerRequest.getName());
        center.setAddress(centerRequest.getAddress());
        center.setEmail(centerRequest.getEmail());
        center.setPhone(centerRequest.getPhone());
        center.setArea(area);

        Center newCenter = centerRepository.save(center);

        saveManagement(newCenter, manager);

        return newCenter;
    }

    private void saveManagement(Center center, Manager manager){
        Management management = new Management();
        management.setCenter(center);
        management.setManager(manager);
        management.setStartDate(new Date());

        managementService.save(management);
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

        centerValidator.validateUpdate(centerRequest, center);

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
        saveEnrollment(fresher, course);

        return true;
    }

    private void saveEnrollment(Fresher fresher, Course course){
        Enrollment enrollment = new Enrollment();
        enrollment.setFresher(fresher);
        enrollment.setCourse(course);
        enrollment.setStartDate(new Date());

        enrollmentService.save(enrollment);
    }

    @Override
    @Transactional
    public boolean merge(Long idCenter1, Long idCenter2, CenterRequest newCenterInfo) {
        Center center1 = getActiveCenterById(idCenter1);
        Center center2 = getActiveCenterById(idCenter2);

        Center newCenter = handleCenterMerge(center1, center2, newCenterInfo);

        saveHistory(center1, center2, newCenter);
        return true;
    }

    private Center handleCenterMerge(Center center1, Center center2, CenterRequest newCenterInfo){
        if(newCenterInfo.getId() == null){
            Center newCenter = create(newCenterInfo);
            updateCourseAndDeleteCenter(center1, newCenter);
            updateCourseAndDeleteCenter(center2, newCenter);
            return newCenter;
        }

        Long idNewCenter = newCenterInfo.getId();
        Long idCenter1 = center1.getId();
        Long idCenter2 = center2.getId();

        if(!idNewCenter.equals(idCenter1) && !idNewCenter.equals(idCenter2)){
            throw new ValidationException("The Center after merging must be one of the two previous Centers!");
        }

        if(idNewCenter.equals(idCenter1)){
            updateCourseAndDeleteCenter(center2, center1);
            changeManager(center1, newCenterInfo.getManagerId());
            return center1;
        }

        updateCourseAndDeleteCenter(center1, center2);
        changeManager(center2, newCenterInfo.getManagerId());
        return center2;
    }

    private void updateCourseAndDeleteCenter(Center oldCenter, Center newCenter){
        courseService.updateCenterIdForActiveCourse(oldCenter.getId(), newCenter.getId());
        deleteById(oldCenter.getId());
    }

    private void saveHistory(Center center1, Center center2, Center newCenter){
        History history = new History();
        history.setDate(new Date());
        history.setNewCenter(newCenter);
        history.setOldCenter1(center1);
        history.setOldCenter2(center2);
        historyService.save(history);
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
            managementService.updateEndDateByCenterIdAndManagerId(center.getId(), mangerId);

            saveManagement(center, newManager);
        }
        return true;
    }

}
