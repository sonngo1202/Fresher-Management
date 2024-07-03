package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Enrollment;
import com.example.fresher_manager.repository.EnrollmentRepository;
import com.example.fresher_manager.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollementServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Override
    public boolean save(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
        return true;
    }
}
