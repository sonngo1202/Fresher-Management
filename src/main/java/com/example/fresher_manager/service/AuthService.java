package com.example.fresher_manager.service;

import com.example.fresher_manager.dto.BearerToken;
import com.example.fresher_manager.dto.LoginRequest;
import com.example.fresher_manager.dto.TokenRefreshRequest;
import com.example.fresher_manager.entity.User;

public interface AuthService {
    void savedAdmin();
    BearerToken login(LoginRequest loginRequestDTO);
    BearerToken refreshAccessToken(TokenRefreshRequest tokenRefreshRequest);
    void validateUser(User user);
}
