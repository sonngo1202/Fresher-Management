package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, Long> {
}
