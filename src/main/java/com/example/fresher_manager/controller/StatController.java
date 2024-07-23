package com.example.fresher_manager.controller;

import com.example.fresher_manager.security.JwtTokenUtil;
import com.example.fresher_manager.service.CenterStatService;
import com.example.fresher_manager.service.ScoreStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats/")
public class StatController {

    private final CenterStatService centerStatService;
    private final ScoreStatService scoreStatService;

    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/fresher-center/filter")
    public ResponseEntity<?> getFresherCountByCenterWithPeriod(@RequestParam("startDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                               @RequestParam("endDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                               @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(centerStatService.getFresherCountByCenterWithPeriod(startDate, endDate, getUsernameByToken(token.substring(7))));
    }

    @GetMapping("/fresher-center")
    public ResponseEntity<?> getFresherCountByCenter(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(centerStatService.getFresherCountByCenterWithCurrentDate(getUsernameByToken(token.substring(7))));
    }

    @GetMapping("/fresher-score")
    public ResponseEntity<?> getFresherCountByScore(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(scoreStatService.getFresherCountByScore(getUsernameByToken(token.substring(7))));
    }

    private String getUsernameByToken(String token){
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}
