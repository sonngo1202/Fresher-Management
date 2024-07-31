package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.ScoreStat;
import com.example.fresher_manager.repository.IFresherRepository;
import com.example.fresher_manager.service.RoleService;
import com.example.fresher_manager.service.ScoreStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ScoreStatServiceImpl implements ScoreStatService {

    private final IFresherRepository fresherRepository;

    private final RoleService roleCheckService;

    @Override
    public List<ScoreStat> getFresherCountByScore(String username) {
        List<ScoreStat> scoreStats = new ArrayList<>();
        Map<String, Long> countMap = new HashMap<>();

        preScoreStat(countMap);

        List<Object[]> results = getResults(username);

        for (Object[] result : results) {
            String range = (String) result[0];
            long count = ((Number) result[1]).longValue();
            countMap.put(range, count);
        }

        createScoreStats(scoreStats, countMap);
        scoreStats.sort(Comparator.comparing(ScoreStat::getFresherCount).reversed()
                .thenComparing(ScoreStat::getScoreRange));

        return scoreStats;
    }

    private List<Object[]> getResults(String username){
        if(roleCheckService.isAdmin()){
            return fresherRepository.getFresherCountByScoreRanges();
        }
        return fresherRepository.getFresherCountByScoreRangesAndManagerUsername(username);
    }

    private void preScoreStat(Map<String, Long> countMap){
        countMap.put("0-4", 0L);
        countMap.put("4-6", 0L);
        countMap.put("6-8", 0L);
        countMap.put("8-9", 0L);
        countMap.put("9-10", 0L);
    }

    private void createScoreStats(List<ScoreStat> scoreStats, Map<String, Long> countMap){
        for(Map.Entry<String, Long> entry : countMap.entrySet()){
            scoreStats.add(new ScoreStat(entry.getKey(), entry.getValue()));
        }
    }

}
