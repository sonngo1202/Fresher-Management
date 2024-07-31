package com.example.fresher_manager.service;

import com.example.fresher_manager.entity.Role;
import com.example.fresher_manager.entity.RoleName;

public interface RoleService {
    boolean isAdmin();
    boolean isManager();
    Role findByName(RoleName name);
}
