package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.id = :id AND c.endDate > CURRENT_DATE")
    Optional<Course> findByIdAndEndDateAfterNow(@Param("id") Long id);
    Optional<Course> findByIdAndCenterId(Long id, Long centerId);
}
