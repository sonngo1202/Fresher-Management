package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.dto.BearerToken;
import com.example.fresher_manager.dto.LoginRequest;
import com.example.fresher_manager.dto.TokenRefreshRequest;
import com.example.fresher_manager.entity.Admin;
import com.example.fresher_manager.repository.UserRepository;
import com.example.fresher_manager.security.CustomUserDetailsService;
import com.example.fresher_manager.security.JwtTokenUtil;
import com.example.fresher_manager.service.AuthService;
import com.example.fresher_manager.service.ValidateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    private final ValidateUserService validateUserService;

    @Override
    public void savedAdmin() {
        Admin admin = new Admin();
        admin.setFirstname("Son");
        admin.setLastname("Ngo");
        admin.setEmail("sonngo@gmail.com");
        admin.setDob(java.sql.Date.valueOf(LocalDate.of(2002, 2, 12)));
        admin.setPhone("0562202977");
        admin.setUsername("ngoson");
        admin.setPassword(passwordEncoder.encode("@Son12345"));
        validateUserService.validateUser(admin);
        userRepository.save(admin);
    }

    @Override
    public BearerToken login(LoginRequest loginRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());

        final String accessToken = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return new BearerToken(accessToken, refreshToken);
    }

    @Override
    public BearerToken refreshAccessToken(TokenRefreshRequest tokenRefreshRequest) {
        String refreshToken = tokenRefreshRequest.getRefreshToken();
        String newAccessToken =  jwtTokenUtil.generateAccessTokenFromRefreshToken(refreshToken);
        return new BearerToken(newAccessToken, refreshToken);
    }

}
