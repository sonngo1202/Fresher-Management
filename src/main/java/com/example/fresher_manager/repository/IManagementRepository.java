package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IManagementRepository extends JpaRepository<Management, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Management m SET m.endDate = CURRENT_TIMESTAMP WHERE m.center.id = :centerId AND m.manager.id = :managerId")
    void updateEndDateByCenterIdAndManagerId(Long centerId, Long managerId);
}
