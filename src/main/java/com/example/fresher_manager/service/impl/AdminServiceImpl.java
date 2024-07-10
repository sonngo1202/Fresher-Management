package com.example.fresher_manager.service.impl;

import com.example.fresher_manager.entity.Admin;
import com.example.fresher_manager.repository.IAdminRepository;
import com.example.fresher_manager.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final PasswordEncoder passwordEncoder;
    private final IAdminRepository adminRepository;


    @Override
    public void saveDefault() {
        Admin admin = new Admin();
        admin.setFirstname("Son");
        admin.setLastname("Ngo");
        admin.setEmail("sonngo@gmail.com");
        admin.setDob(java.sql.Date.valueOf(LocalDate.of(2002, 2, 12)));
        admin.setPhone("0562202977");
        admin.setUsername("ngoson");
        admin.setPassword(passwordEncoder.encode("@Son12345"));

        adminRepository.save(admin);
    }

    @Override
    public Admin getActiveUserById(Long id) {
        return null;
    }

    @Override
    public boolean save(Admin user) {
        return false;
    }
}
