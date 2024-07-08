package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Enrollment;

public interface EnrollmentService {
    boolean save(Enrollment enrollment);
    boolean isFresherEnrolledInCourse(Long fresherId, Long courseId);
    boolean updateEndDateByFresherId(Long fresherId);
}