package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.CenterStat;
import com.example.fresher_manager.repository.ICenterRepository;
import com.example.fresher_manager.service.CenterStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterStatServiceImpl implements CenterStatService {

    private final ICenterRepository centerRepository;

    @Override
    public List<CenterStat> getFresherCountByCenter(Date statisticStartDate, Date statisticEndDate) {
        return centerRepository.getFresherCountByCenter(statisticStartDate, statisticEndDate);
    }
}
