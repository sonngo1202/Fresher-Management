package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Role;
import com.example.fresher_manager.entity.RoleName;
import com.example.fresher_manager.repository.IRoleRepository;
import com.example.fresher_manager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final IRoleRepository roleRepository;

    @Override
    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }

    @Override
    public boolean isManager() {
        return hasRole("ROLE_MANAGER");
    }

    @Override
    public Role findByName(RoleName name) {
        return roleRepository.findByRoleName(name);
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
