package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.ScoreStat;
import com.example.fresher_manager.repository.IFresherRepository;
import com.example.fresher_manager.service.ScoreStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreStatServiceImpl implements ScoreStatService {

    private final IFresherRepository fresherRepository;

    @Override
    public List<ScoreStat> getFresherCountByScore() {
        List<ScoreStat> scoreStats = new ArrayList<>();
        createScoreStats(scoreStats);

        for(ScoreStat scoreStat : scoreStats){
            scoreStat.setFresherCount(fresherRepository.getFresherCountByScore(scoreStat.getFirstScore(), scoreStat.getSecondScore()));
        }

        return scoreStats;
    }

    private void createScoreStats(List<ScoreStat> scoreStats){
        scoreStats.add(new ScoreStat("0-4", 0, 4, 0));
        scoreStats.add(new ScoreStat("4-6", 4, 6, 0));
        scoreStats.add(new ScoreStat("6-8", 6, 8, 0));
        scoreStats.add(new ScoreStat("8-9", 8, 9, 0));
        scoreStats.add(new ScoreStat("9-10", 9, 10, 0));
    }
}
