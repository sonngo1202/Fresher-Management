package com.example.fresher_manager.service;

import com.example.fresher_manager.dto.ResultRequest;
import com.example.fresher_manager.entity.Fresher;

import java.util.List;

public interface FresherService extends UserService<Fresher>{
    boolean deleteById(Long id);
    boolean updateById(Long id, Fresher fresher);
    boolean scoreFresher(Long fresherId, ResultRequest resultRequest);
    List<Fresher> findAllByName();
    List<Fresher> findAllByEmail();
    List<Fresher> findAllByLanguage();
}
