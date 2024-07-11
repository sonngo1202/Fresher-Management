package com.example.fresher_manager.controller;

import com.example.fresher_manager.service.CenterStatService;
import com.example.fresher_manager.service.ScoreStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats/")
public class StatController {

    private final CenterStatService centerStatService;
    private final ScoreStatService scoreStatService;

    @GetMapping("/fresher-center")
    public ResponseEntity<?> getFresherCountByCenter(@RequestParam("startDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                     @RequestParam("endDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return ResponseEntity.ok(centerStatService.getFresherCountByCenter(startDate, endDate));
    }

    @GetMapping("/fresher-score")
    public ResponseEntity<?> getFresherCountByScore(){
        return ResponseEntity.ok(scoreStatService.getFresherCountByScore());
    }

}
