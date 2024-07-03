package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.dto.AddFresherRequest;
import com.example.fresher_manager.dto.ResultRequest;
import com.example.fresher_manager.dto.UpdateFresherRequest;
import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.exception.error.EmailAlreadyExistsException;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.FresherRepository;
import com.example.fresher_manager.repository.UserRepository;
import com.example.fresher_manager.service.FresherService;
import com.example.fresher_manager.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {

    private final LanguageService languageService;
    private final AuthServiceImpl authService;
    private final UserRepository userRepository;
    private final FresherRepository fresherRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean add(AddFresherRequest addFresherRequest) {
        Fresher fresher = new Fresher();
        fresher.setFirstname(addFresherRequest.getFirstname());
        fresher.setLastname(addFresherRequest.getLastname());
        fresher.setDob(addFresherRequest.getDob());
        fresher.setEmail(addFresherRequest.getEmail());
        fresher.setPhone(addFresherRequest.getPhone());
        fresher.setUsername(addFresherRequest.getUsername());
        fresher.setPassword(passwordEncoder.encode(addFresherRequest.getPassword()));
        fresher.setCode(addFresherRequest.getCode());

        authService.validateUser(fresher);
        validateFresher(addFresherRequest.getLanguageId());

        fresher.setLanguage(languageService.get(addFresherRequest.getLanguageId()));

        userRepository.save(fresher);

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Fresher fresher = findById(id);
        fresher.setStatus(false);
        userRepository.save(fresher);
        return true;
    }

    @Override
    public boolean update(Long id, UpdateFresherRequest updateFresherRequest) {
        Fresher fresher = findById(id);

        fresher.setFirstname(updateFresherRequest.getFirstname());
        fresher.setLastname(updateFresherRequest.getLastname());
        fresher.setDob(updateFresherRequest.getDob());
        fresher.setEmail(updateFresherRequest.getEmail());
        fresher.setPhone(updateFresherRequest.getPhone());

        if(!fresher.getEmail().equals(updateFresherRequest.getEmail())){
            if(userRepository.findByEmail(fresher.getEmail()) != null){
                throw new EmailAlreadyExistsException("Email is already taken!");
            }
        }
        validateFresher(updateFresherRequest.getLanguageId());

        fresher.setLanguage(languageService.get(updateFresherRequest.getLanguageId()));

        userRepository.save(fresher);

        return true;
    }

    @Override
    public boolean scoreFresher(Long fresherId, ResultRequest resultRequest) {
        return false;
    }

    @Override
    public List<Fresher> getAll() {
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
    public Fresher findById(Long id) {
        return fresherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with id: " + id));
    }

    @Override
    public void validateFresher(Long languageId) {
        if(!languageService.existsById(languageId)){
            throw new ResourceNotFoundException("Language not found with id: " + languageId);
        }
    }
}
