package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Language;
import com.example.fresher_manager.repository.LanguageRepository;
import com.example.fresher_manager.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public Language get(Long id) {
        return languageRepository.getById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return languageRepository.existsById(id);
    }
}
