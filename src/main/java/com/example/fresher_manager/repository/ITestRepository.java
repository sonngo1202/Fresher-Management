package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestRepository extends JpaRepository<Test, Long> {
}
