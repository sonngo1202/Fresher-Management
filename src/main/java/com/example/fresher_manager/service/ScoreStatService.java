package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.ScoreStat;

import java.util.List;

public interface ScoreStatService {
    List<ScoreStat> getFresherCountByScore(String token);
}
