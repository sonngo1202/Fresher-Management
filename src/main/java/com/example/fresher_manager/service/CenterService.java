package com.example.fresher_manager.service;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.entity.Course;

import java.util.List;

public interface CenterService {
    List<Center> getAll();
    boolean create(CenterRequest centerRequest);
    boolean delete(Long id);
    boolean update(Long id, CenterRequest centerRequest);
    boolean changeManager(Long id, Long managerId);
    boolean addCourse(Long id, Course course);
    boolean assignFresherToCenter(Long fresherId, Long courseId);
    boolean merge();
    Center findCenterById(Long id);
    void validateCenter(CenterRequest centerRequest);
}
