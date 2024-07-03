package com.example.fresher_manager.service;

import com.example.fresher_manager.dto.AddFresherRequest;
import com.example.fresher_manager.dto.ResultRequest;
import com.example.fresher_manager.dto.UpdateFresherRequest;
import com.example.fresher_manager.entity.Fresher;

import java.util.List;

public interface FresherService {
    boolean add(AddFresherRequest addFresherRequest);
    boolean delete(Long id);
    boolean update(Long id, UpdateFresherRequest updateFresherRequest);
    boolean scoreFresher(Long fresherId, ResultRequest resultRequest);
    List<Fresher> getAll();
    List<Fresher> findAllByName();
    List<Fresher> findAllByEmail();
    List<Fresher> findAllByLanguage();
    Fresher findById(Long id);
    void validateFresher(Long id);
}
