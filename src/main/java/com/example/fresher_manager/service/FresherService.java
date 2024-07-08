package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.Result;

import java.util.List;

public interface FresherService extends UserService<Fresher>{
    boolean deleteById(Long id);
    boolean updateById(Long id, Fresher fresher);
    boolean scoringForFresher(Long id, Result result);
    List<Fresher> findAllByName(String token, String keyword);
    List<Fresher> findAllByEmail(String token, String keyword);
    List<Fresher> findAllByLanguage(String token, String keyword);
    List<Fresher> findAll(String token);
}
