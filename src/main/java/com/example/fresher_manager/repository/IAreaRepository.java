package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAreaRepository extends JpaRepository<Area, Long> {
    Optional<Area> findByIdAndStatusTrue(Long id);
}
