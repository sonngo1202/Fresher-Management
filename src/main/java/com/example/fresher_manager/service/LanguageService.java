package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Language;

public interface LanguageService {
    Language get(Long id);
    boolean existsById(Long id);
}
