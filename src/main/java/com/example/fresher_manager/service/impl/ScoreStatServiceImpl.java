package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.ScoreStat;
import com.example.fresher_manager.repository.IFresherRepository;
import com.example.fresher_manager.security.JwtTokenUtil;
import com.example.fresher_manager.service.RoleCheckService;
import com.example.fresher_manager.service.ScoreStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScoreStatServiceImpl implements ScoreStatService {

    private final IFresherRepository fresherRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final RoleCheckService roleCheckService;

    @Override
    public List<ScoreStat> getFresherCountByScore(String token) {
        List<ScoreStat> scoreStats = new ArrayList<>();
        Map<String, Long> countMap = new HashMap<>();

        preScoreStat(countMap);

        List<Object[]> results;

        if(roleCheckService.isAdmin()){
            results = fresherRepository.getFresherCountByScoreRanges();
        }else{
            results = fresherRepository.getFresherCountByScoreRangesAndManagerUsername(jwtTokenUtil.getUsernameFromToken(token));
        }

        for (Object[] result : results) {
            String range = (String) result[0];
            long count = ((Number) result[1]).longValue();
            countMap.put(range, count);
        }

        createScoreStats(scoreStats, countMap);

        return scoreStats;
    }

    private void preScoreStat(Map<String, Long> countMap){
        countMap.put("0-4", 0L);
        countMap.put("4-6", 0L);
        countMap.put("6-8", 0L);
        countMap.put("8-9", 0L);
        countMap.put("9-10", 0L);
    }

    private void createScoreStats(List<ScoreStat> scoreStats, Map<String, Long> countMap){
        scoreStats.add(new ScoreStat("0-4", countMap.get("0-4")));
        scoreStats.add(new ScoreStat("4-6", countMap.get("4-6")));
        scoreStats.add(new ScoreStat("6-8", countMap.get("6-8")));
        scoreStats.add(new ScoreStat("8-9", countMap.get("8-9")));
        scoreStats.add(new ScoreStat("9-10", countMap.get("9-10")));
    }

}
