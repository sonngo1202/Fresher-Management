package com.example.fresher_manager.controller;

import com.example.fresher_manager.dto.AddFresherRequest;
import com.example.fresher_manager.dto.UpdateFresherRequest;
import com.example.fresher_manager.service.FresherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fresher")
public class FresherController {
    private final FresherService fresherService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AddFresherRequest fresherRequest){
        fresherService.add(fresherRequest);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id){
        fresherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateFresherRequest updateFresherRequest){
        fresherService.update(id, updateFresherRequest);
        return ResponseEntity.ok().build();
    }
}
