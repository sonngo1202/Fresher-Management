package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Language;
import com.example.fresher_manager.exception.error.ResourceNotFoundException;
import com.example.fresher_manager.repository.ILanguageRepository;
import com.example.fresher_manager.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LanguageServiceImpl implements LanguageService {

    private final ILanguageRepository languageRepository;

    @Override
    public Language findById(Long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Language not found with id: " + id);
                    return new ResourceNotFoundException("Language not found with id: " + id);
                });
    }
}
