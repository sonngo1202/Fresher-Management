package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Course;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.ICourseRepository;
import com.example.fresher_manager.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final ICourseRepository courseRepository;

    @Override
    public boolean save(Course course) {
        courseRepository.save(course);
        return true;
    }

    @Override
    public Course getActiveCourseById(Long id) {
        return courseRepository.findByIdAndEndDateAfterNow(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @Override
    public boolean isCourseBelongToCenter(Long id, Long centerId) {
        Optional<Course> course = courseRepository.findByIdAndCenterId(id, centerId);
        return course.isPresent();
    }

    @Override
    public boolean updateCenterIdForActiveCourse(Long currentCentId, Long newCenterId) {
        courseRepository.updateCenterIdForActiveCourses(currentCentId, newCenterId);
        return true;
    }

}