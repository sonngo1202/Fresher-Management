package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    boolean existsByIdAndStatusTrue(Long id);
}
