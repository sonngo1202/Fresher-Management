package com.example.fresher_manager.controller;

import com.example.fresher_manager.entity.Manager;
import com.example.fresher_manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/add")
    public ResponseEntity<?> add(Manager manager){
        managerService.add(manager);
        return ResponseEntity.status(201).build();
    }


}
