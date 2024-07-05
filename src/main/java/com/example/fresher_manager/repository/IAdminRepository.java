package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByIdAndStatusTrue(Long id);
}
