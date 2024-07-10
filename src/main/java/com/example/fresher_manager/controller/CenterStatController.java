package com.example.fresher_manager.controller;

import com.example.fresher_manager.service.CenterStatService;
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
public class CenterStatController {

    private final CenterStatService centerStatService;

    @GetMapping("/fresher-center")
    public ResponseEntity<?> getFresherCountByCenter(@RequestParam("startDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                     @RequestParam("endDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return ResponseEntity.ok(centerStatService.getFresherCountByCenter(startDate, endDate));
    }

}
