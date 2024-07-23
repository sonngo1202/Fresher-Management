package com.example.fresher_manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.fresher_manager.entity.ScoreStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

@SpringBootTest
public class ScoreStatServiceTest {

    @Autowired
    private ScoreStatService scoreStatService;

    @Test
    @WithMockUser(username = "ngoson", roles = {"ADMIN"})
    void getFresherCountByScoreWithAdmin(){
        List<ScoreStat> scoreStats = scoreStatService.getFresherCountByScore("ngoson");

        assertEquals(new ScoreStat("6-8", 1L), scoreStats.get(0));
        assertEquals(new ScoreStat("0-4", 0L), scoreStats.get(1));
        assertEquals(new ScoreStat("4-6", 0L), scoreStats.get(2));
        assertEquals(new ScoreStat("8-9", 0L), scoreStats.get(3));
        assertEquals(new ScoreStat("9-10", 0L), scoreStats.get(4));
    }

    @Test
    @WithMockUser(username = "tranthu", roles = {"MANAGER"})
    void getFresherCountByScoreWithManager(){
        List<ScoreStat> scoreStats = scoreStatService.getFresherCountByScore("tranthu");

        assertEquals(new ScoreStat("0-4", 0L), scoreStats.get(0));
        assertEquals(new ScoreStat("4-6", 0L), scoreStats.get(1));
        assertEquals(new ScoreStat("6-8", 0L), scoreStats.get(2));
        assertEquals(new ScoreStat("8-9", 0L), scoreStats.get(3));
        assertEquals(new ScoreStat("9-10", 0L), scoreStats.get(4));
    }
}
