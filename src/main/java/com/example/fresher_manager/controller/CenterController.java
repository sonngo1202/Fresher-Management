package com.example.fresher_manager.controller;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/center")
public class CenterController {

    private final CenterService centerService;

    public ResponseEntity<?> create(@RequestBody CenterRequest centerRequest){
        centerService.create(centerRequest);
        return ResponseEntity.status(201).build();
    }
}
