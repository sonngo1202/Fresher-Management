package com.example.fresher_manager.controller;

import com.example.fresher_manager.dto.CenterRequest;
import com.example.fresher_manager.entity.Course;
import com.example.fresher_manager.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/centers")
public class CenterController {

    private final CenterService centerService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CenterRequest centerRequest){
        centerService.create(centerRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(centerService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        centerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody CenterRequest centerRequest){
        centerService.update(id, centerRequest);
        return ResponseEntity.ok("Center updated successfully");
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<?> addCourse(@PathVariable Long id, @RequestBody Course course){
        centerService.addCourse(id, course);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/courses/{courseId}/freshers/{fresherId}")
    public ResponseEntity<?> assignFresherToCenter(@PathVariable Long fresherId, @PathVariable Long courseId){
        centerService.assignFresherToCenter(fresherId, courseId);
        return ResponseEntity.ok().build();
    }

}
