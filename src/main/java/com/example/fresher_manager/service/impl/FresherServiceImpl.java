package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Result;
import com.example.fresher_manager.entity.Test;
import com.example.fresher_manager.exception.error.MaxTestCompletedException;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.exception.error.ValidationException;
import com.example.fresher_manager.repository.IFresherRepository;
import com.example.fresher_manager.security.JwtTokenUtil;
import com.example.fresher_manager.service.*;
import com.example.fresher_manager.validator.FresherValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {
    public static final int MAX_TESTS = 3;

    private final LanguageService languageService;
    private final IFresherRepository fresherRepository;
    private final PasswordEncoder passwordEncoder;
    private final FresherValidator fresherValidator;
    private final RoleCheckService roleCheckService;
    private final JwtTokenUtil jwtTokenUtil;
    private final TestService testService;
    private final ResultService resultService;

    @Override
    public boolean save(Fresher user) {
        fresherValidator.validateCode(user.getCode());
        fresherValidator.validateUsername(user.getUsername());
        fresherValidator.validateEmail(user.getEmail());
        fresherValidator.validatePhone(user.getPhone());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLanguage(languageService.findById(user.getLanguage().getId()));

        fresherRepository.save(user);

        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        Fresher fresher = getActiveUserById(id);
        fresher.setStatus(false);
        fresherRepository.save(fresher);
        return true;
    }

    @Override
    public boolean updateById(Long id, Fresher updateFresher) {
        Fresher fresher = getActiveUserById(id);

        if(!updateFresher.getEmail().equalsIgnoreCase(fresher.getEmail())){
            fresherValidator.validateEmail(updateFresher.getEmail());
        }

        if(!updateFresher.getPhone().equalsIgnoreCase(fresher.getPhone())){
            fresherValidator.validatePhone(updateFresher.getPhone());
        }

        fresher.setFirstname(updateFresher.getFirstname());
        fresher.setLastname(updateFresher.getLastname());
        fresher.setDob(updateFresher.getDob());
        fresher.setEmail(updateFresher.getEmail());
        fresher.setPhone(updateFresher.getPhone());

        fresher.setLanguage(languageService.findById(updateFresher.getLanguage().getId()));

        fresherRepository.save(fresher);

        return true;
    }

    @Override
    @Transactional
    public boolean scoringForFresher(Long id, Result result) {
        Fresher fresher = getActiveUserById(id);
        if(fresher.getResults().size() >= MAX_TESTS){
            throw new MaxTestCompletedException("The number of fresher tests has been maxed");
        }

        Test test = testService.findById(result.getTest().getId());

        result.setFresher(fresher);
        result.setTest(test);

        resultService.save(result);

        return true;
    }

    @Override
    public List<Fresher> findAll(String token) {
        if(roleCheckService.isAdmin()){
            return fresherRepository.findAll();
        }
        if(roleCheckService.isManager()){
            return fresherRepository.findByManagerUsername(jwtTokenUtil.getUsernameFromToken(token));
        }
        return null;
    }

    @Override
    public List<Fresher> findAllByName(String token, String keyword) {
        if(keyword == null || keyword.trim().isEmpty()){
            throw new ValidationException("Keyword must not be empty");
        }

        List<Fresher> listAll = findAll(token);

        return listAll.stream()
                .filter(fresher ->
                        fresher.getFirstname().toLowerCase().contains(keyword.toLowerCase()) ||
                        fresher.getLastname().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public List<Fresher> findAllByEmail(String token, String keyword) {
        if(keyword == null || keyword.trim().isEmpty()){
            throw new ValidationException("Keyword must not be empty");
        }

        List<Fresher> listAll = findAll(token);

        return listAll.stream()
                .filter(fresher -> fresher.getEmail().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public List<Fresher> findAllByLanguage(String token, String keyword) {
        if(keyword == null || keyword.trim().isEmpty()){
            throw new ValidationException("Keyword must not be empty");
        }

        List<Fresher> listAll = findAll(token);

        return listAll.stream()
                .filter(fresher -> fresher.getLanguage().getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public Fresher getActiveUserById(Long id) {
        return fresherRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with id: " + id));
    }
}
