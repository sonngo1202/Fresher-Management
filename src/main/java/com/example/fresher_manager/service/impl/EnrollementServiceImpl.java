package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Enrollment;
import com.example.fresher_manager.repository.IEnrollmentRepository;
import com.example.fresher_manager.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollementServiceImpl implements EnrollmentService {

    private final IEnrollmentRepository enrollmentRepository;

    @Override
    public boolean save(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
        return true;
    }

    @Override
    public boolean isFresherEnrolledInCourse(Long fresherId, Long courseId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findByFresherIdAndCourseId(fresherId, courseId);
        return enrollment.isPresent();
    }

    @Override
    public boolean updateEndDateByFresherId(Long fresherId) {
        enrollmentRepository.updateEndDateByFresherId(fresherId);
        return true;
    }
}
