package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.CenterStat;

import java.util.Date;
import java.util.List;

public interface CenterStatService {
    List<CenterStat> getFresherCountByCenter(Date statisticStartDate, Date statisticEndDate);
}
