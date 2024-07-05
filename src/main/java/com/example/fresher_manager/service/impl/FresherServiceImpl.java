package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.dto.ResultRequest;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.IFresherRepository;
import com.example.fresher_manager.service.FresherService;
import com.example.fresher_manager.service.LanguageService;
import com.example.fresher_manager.validator.FresherValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {

    private final LanguageService languageService;
    private final IFresherRepository fresherRepository;
    private final PasswordEncoder passwordEncoder;
    private final FresherValidator fresherValidator;

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
    public boolean scoreFresher(Long fresherId, ResultRequest resultRequest) {
        return false;
    }

    @Override
    public List<Fresher> findAll() {
        return List.of();
    }

    @Override
    public List<Fresher> findAllByName() {
        return List.of();
    }

    @Override
    public List<Fresher> findAllByEmail() {
        return List.of();
    }

    @Override
    public List<Fresher> findAllByLanguage() {
        return List.of();
    }

    @Override
    public Fresher getActiveUserById(Long id) {
        return fresherRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with id: " + id));
    }
}
