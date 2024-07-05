package com.example.fresher_manager.service;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.entity.Course;

import java.util.List;

public interface CenterService {
    List<Center> getAll();
    boolean create(CenterRequest centerRequest);
    boolean deleteById(Long id);
    boolean updateById(Long id, CenterRequest centerRequest);
    boolean addCourseById(Long id, Course course);
    boolean assignFresherToCenter(Long id, Long fresherId, Long courseId);
    boolean merge(Long idCenter1, Long idCenter2, CenterRequest newCenter);
    Center getActiveCenterById(Long id);
}
