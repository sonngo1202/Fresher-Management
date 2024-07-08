package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Result;
import com.example.fresher_manager.repository.IResultRepository;
import com.example.fresher_manager.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final IResultRepository resultRepository;

    @Override
    public boolean save(Result result) {
        resultRepository.save(result);
        return true;
    }
}
