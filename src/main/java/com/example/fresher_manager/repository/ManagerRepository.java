package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findAllByStatusTrue();
}
