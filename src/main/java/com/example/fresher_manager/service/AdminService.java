package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Admin;

public interface AdminService extends UserService<Admin> {
    void saveDefault();
}
