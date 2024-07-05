package com.example.fresher_manager.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final boolean status;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean status){
        super(username, password, authorities);
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }
}
