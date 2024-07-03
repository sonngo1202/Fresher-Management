package com.example.fresher_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultRequest {
    private LocalDateTime testDateTime;
    private float score;
    private String comment;
    private Long testId;
}
