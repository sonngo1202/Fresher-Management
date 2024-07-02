package com.example.fresher_manager.controller;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/center")
public class CenterController {

    private final CenterService centerService;

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody CenterRequest centerRequest){
        centerService.create(centerRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(centerService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        centerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody CenterRequest centerRequest){
        centerService.update(id, centerRequest);
        return ResponseEntity.ok("Center updated successfully");
    }
}
