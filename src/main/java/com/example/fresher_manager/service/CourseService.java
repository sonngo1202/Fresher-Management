package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Course;

import java.util.List;

public interface CourseService {
    boolean save(Course course);
    Course getActiveCourseById(Long id);
    boolean isCourseBelongToCenter(Long id, Long centerId);
    boolean updateCenterIdForActiveCourse(Long currentCentId, Long newCenterId);
}
