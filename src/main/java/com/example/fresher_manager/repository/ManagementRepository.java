package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementRepository extends JpaRepository<Management, Long> {
    @Query("SELECT m FROM Management m WHERE m.center.id = :centerId AND m.manager.id = :managerId")
    Management findByCenterIdAndManagerId(@Param("centerId") Long centerId, @Param("managerId") Long managerId);
}
