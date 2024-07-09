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
        centerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody CenterRequest centerRequest){
        centerService.updateById(id, centerRequest);
        return ResponseEntity.ok("Center updated successfully");
    }

    @PostMapping("/{id}/courses")
    public ResponseEntity<?> addCourse(@PathVariable Long id, @RequestBody Course course){
        centerService.addCourseById(id, course);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/courses/{courseId}/freshers/{fresherId}")
    public ResponseEntity<?> assignFresherToCenter(@PathVariable Long id, @PathVariable Long fresherId, @PathVariable Long courseId){
        centerService.assignFresherToCenter(id, fresherId, courseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{idCenter1}/merge/{idCenter2}")
    public ResponseEntity<?> merge(@PathVariable Long idCenter1, @PathVariable Long idCenter2, @RequestBody CenterRequest newCenterInfo){
        centerService.merge(idCenter1, idCenter2, newCenterInfo);
        return ResponseEntity.ok().build();
    }

}
