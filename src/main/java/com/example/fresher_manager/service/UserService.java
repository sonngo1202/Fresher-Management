package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.User;

import java.util.List;

public interface UserService<T extends User>{
    T findById(Long id);
    List<T> findAll();
    boolean save(T user);
}
