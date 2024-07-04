package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, Long> {
    Fresher findByCode(String code);
    Fresher findByUsername(String username);
    Fresher findByEmail(String email);
    Fresher findByPhone(String phone);
}
