package com.example.fresher_manager.controller;

import com.example.fresher_manager.dto.LoginRequest;
import com.example.fresher_manager.dto.TokenRefreshRequest;
import com.example.fresher_manager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(@RequestBody TokenRefreshRequest tokenRefreshRequest){
        return ResponseEntity.ok(authService.refreshAccessToken(tokenRefreshRequest));
    }
}
