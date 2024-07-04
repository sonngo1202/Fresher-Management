package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    Center findByName(String name);
    Center findByPhone(String phone);
    Center findByEmail(String email);
}