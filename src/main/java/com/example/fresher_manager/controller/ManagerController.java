package com.example.fresher_manager.controller;

import com.example.fresher_manager.entity.Manager;
import com.example.fresher_manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Manager manager){
        managerService.add(manager);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getALl(){
        return ResponseEntity.ok(managerService.getAll());
    }

}
