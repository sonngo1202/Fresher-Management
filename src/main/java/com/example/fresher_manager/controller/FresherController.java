package com.example.fresher_manager.controller;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Result;
import com.example.fresher_manager.security.JwtTokenUtil;
import com.example.fresher_manager.service.FresherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/freshers")
public class FresherController {
    private final FresherService fresherService;

    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Fresher fresher){
        fresherService.save(fresher);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        fresherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Fresher fresher){
        fresherService.updateById(id, fresher);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(fresherService.findAll(getUsernameByToken(token.substring(7))));
    }

    @PostMapping("/{id}/scoring-fresher")
    public ResponseEntity<?> scoringForFresher(@PathVariable Long id, @RequestBody Result result){
        fresherService.scoringForFresher(id, result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/by-name")
    public ResponseEntity<?> getByName(@RequestParam("key") String keyword, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(fresherService.findAllByName(getUsernameByToken(token.substring(7)), keyword));
    }

    @GetMapping("/search/by-email")
    public ResponseEntity<?> getByEmail(@RequestParam("key") String keyword, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(fresherService.findAllByEmail(getUsernameByToken(token.substring(7)), keyword));
    }

    @GetMapping("/search/by-language")
    public ResponseEntity<?> getByLanguage(@RequestParam("key") String keyword, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(fresherService.findAllByLanguage(getUsernameByToken(token.substring(7)), keyword));
    }

    private String getUsernameByToken(String token){
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}
