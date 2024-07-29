package com.example.fresher_manager.controller;

import com.example.fresher_manager.dto.BearerToken;
import com.example.fresher_manager.dto.LoginRequest;
import com.example.fresher_manager.dto.TokenRefreshRequest;
import com.example.fresher_manager.security.JwtTokenUtil;
import com.example.fresher_manager.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/tokens")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequestDTO){
        BearerToken response = authService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/tokens/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody TokenRefreshRequest tokenRefreshRequest){
        return ResponseEntity.ok(authService.refreshAccessToken(tokenRefreshRequest));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(authService.getInfo(jwtTokenUtil.getUsernameFromToken(token.substring(7))));
    }
}
