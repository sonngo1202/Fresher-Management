package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Test;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.ITestRepository;
import com.example.fresher_manager.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final ITestRepository testRepository;

    @Override
    public Test findById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Test not found with id: " + id);
                    return new ResourceNotFoundException("Test not found with id: " + id);
                });
    }
}
