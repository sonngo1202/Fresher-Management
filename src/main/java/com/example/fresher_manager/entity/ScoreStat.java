package com.example.fresher_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScoreStat {
    private String scoreRange;
    private float firstScore;
    private float secondScore;
    private long fresherCount;
}
