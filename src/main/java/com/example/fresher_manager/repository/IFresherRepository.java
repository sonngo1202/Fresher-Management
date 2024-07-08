package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFresherRepository extends JpaRepository<Fresher, Long> {
    Fresher findByCode(String code);
    Fresher findByEmail(String email);
    Fresher findByPhone(String phone);
    Optional<Fresher> findByIdAndStatusTrue(Long id);

    @Query("SELECT f from Fresher f JOIN f.enrollments e JOIN e.course c JOIN c.center ct JOIN ct.managements mm JOIN mm.manager m WHERE m.username = :managerUsername AND mm.endDate IS NULL AND e.endDate IS NULL")
    List<Fresher> findByManagerUsername(@Param("managerUsername") String managerUsername);
}
