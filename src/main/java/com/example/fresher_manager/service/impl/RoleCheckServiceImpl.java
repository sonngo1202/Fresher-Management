package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.service.RoleCheckService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RoleCheckServiceImpl implements RoleCheckService {
    @Override
    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }

    @Override
    public boolean isManager() {
        return hasRole("ROLE_MANAGER");
    }

    private boolean hasRole(String role){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            return false;
        }

        for(GrantedAuthority authority : authentication.getAuthorities()){
            if(authority.getAuthority().equalsIgnoreCase(role)){
                return true;
            }
        }

        return false;
    }
}
