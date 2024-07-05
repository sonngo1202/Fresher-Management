package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IEnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByFresherIdAndCourseId(Long fresherId, Long courseId);

    @Transactional
    @Modifying
    @Query("UPDATE Enrollment e SET e.endDate = CURRENT_TIMESTAMP WHERE e.fresher.id = :fresherId AND e.endDate IS NULL")
    void updateEndDateByFresherId(Long fresherId);
}
