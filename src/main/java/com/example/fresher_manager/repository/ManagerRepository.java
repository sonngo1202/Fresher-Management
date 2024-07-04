package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String email);
    Manager findByPhone(String phone);
    Manager findByUsername(String username);
    List<Manager> findAllByStatusTrue();
    boolean existsByIdAndStatusTrue(Long id);

    @Query("SELECT m.manager FROM Management m WHERE m.center.id = :centerId AND m.endDate IS NULL")
    Manager findManagerByCenterIdAndEndDateIsNull(@Param("centerId") Long centerId);
}
