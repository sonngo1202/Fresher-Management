package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Course;

public interface CourseService {
    boolean save(Course course);
    Course findById(Long id);
}
