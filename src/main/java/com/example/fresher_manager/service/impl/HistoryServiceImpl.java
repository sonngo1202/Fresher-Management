package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.History;
import com.example.fresher_manager.repository.IHistoryRepository;
import com.example.fresher_manager.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final IHistoryRepository historyRepository;

    @Override
    public boolean save(History history) {
        historyRepository.save(history);
        return true;
    }
}
