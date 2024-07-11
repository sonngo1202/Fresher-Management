package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Center;
import com.example.fresher_manager.entity.CenterStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICenterRepository extends JpaRepository<Center, Long> {
    Center findByName(String name);
    Center findByPhone(String phone);
    Center findByEmail(String email);
    Optional<Center> findByIdAndStatusTrue(Long id);
    List<Center> findByStatusTrue();

    @Query("SELECT new com.example.fresher_manager.entity.CenterStat(c.id, c.name, c.address, COUNT(DISTINCT f.id)) " +
            "FROM Center c " +
            "JOIN Course ce ON c.id = ce.center.id " +
            "JOIN Enrollment e ON ce.id = e.course.id " +
            "JOIN Fresher f ON e.fresher.id = f.id " +
            "WHERE c.status IS TRUE " +
            "AND e.startDate >= :statisticStartDate " +
            "AND e.startDate <= :statisticEndDate  " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(DISTINCT f.id) DESC")
    List<CenterStat> getFresherCountByCenter(@Param("statisticStartDate")Date statisticStartDate,
                                             @Param("statisticEndDate")Date statisticEndDate);
}