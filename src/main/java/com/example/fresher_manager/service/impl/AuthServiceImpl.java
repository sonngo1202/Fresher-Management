package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.dto.BearerToken;
import com.example.fresher_manager.dto.LoginRequest;
import com.example.fresher_manager.dto.TokenRefreshRequest;
import com.example.fresher_manager.entity.User;
import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.security.CustomUserDetails;
import com.example.fresher_manager.security.CustomUserDetailsService;
import com.example.fresher_manager.security.JwtTokenUtil;
import com.example.fresher_manager.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    public BearerToken login(LoginRequest loginRequest) {
        authenticate(loginRequest);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        checkDelete(userDetails);

        final String accessToken = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return new BearerToken(accessToken, refreshToken);
    }

    private void authenticate(LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            log.error("Incorrect username or password");
            throw new BadCredentialsException("Incorrect username or password");
        }
    }

    private void checkDelete(UserDetails userDetails){
        CustomUserDetails customUserDetails;
        if (userDetails instanceof CustomUserDetails) {
            customUserDetails = (CustomUserDetails) userDetails;
            if (!customUserDetails.getStatus()) {
                log.error("User account has been deleted");
                throw new ValidationException("User account has been deleted");
            }
            return;
        }
        log.error("Invalid user details");
        throw new ValidationException("Invalid user details");
    }

    @Override
    public BearerToken refreshAccessToken(TokenRefreshRequest tokenRefreshRequest) {
        String refreshToken = tokenRefreshRequest.getRefreshToken();
        String newAccessToken =  jwtTokenUtil.generateAccessTokenFromRefreshToken(refreshToken);
        return new BearerToken(newAccessToken, refreshToken);
    }

}
