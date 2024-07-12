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
            "AND e.startDate <= :statisticEndDate " +
            "AND e.endDate IS NULL " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(DISTINCT f.id) DESC")
    List<CenterStat> getFresherCountByCenterWithPeriod(@Param("statisticStartDate")Date statisticStartDate,
                                             @Param("statisticEndDate")Date statisticEndDate);

    @Query("SELECT new com.example.fresher_manager.entity.CenterStat(c.id, c.name, c.address, COUNT(DISTINCT f.id)) " +
            "FROM Center c " +
            "JOIN Course ce ON c.id = ce.center.id " +
            "JOIN Enrollment e ON ce.id = e.course.id " +
            "JOIN Fresher f ON e.fresher.id = f.id " +
            "WHERE c.status IS TRUE " +
            "AND e.endDate IS NULL " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(DISTINCT f.id) DESC")
    List<CenterStat> getFresherCountByCenterWithCurrentDate();

    @Query("SELECT new com.example.fresher_manager.entity.CenterStat(c.id, c.name, c.address, COUNT(DISTINCT f.id)) " +
            "FROM Center c " +
            "JOIN Course ce ON c.id = ce.center.id " +
            "JOIN Enrollment e ON ce.id = e.course.id " +
            "JOIN Fresher f ON e.fresher.id = f.id " +
            "JOIN Management me ON c.id = me.center.id " +
            "JOIN Manager m ON me.manager.id = m.id " +
            "WHERE c.status IS TRUE " +
            "AND m.username = :managerUsername " +
            "AND me.endDate IS NULL " +
            "AND e.startDate >= :statisticStartDate " +
            "AND e.startDate <= :statisticEndDate " +
            "AND e.endDate IS NULL " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(DISTINCT f.id) DESC")
    List<CenterStat> getFresherCountByCenterWithPeriodAndManagerUsername(@Param("statisticStartDate")Date statisticStartDate,
                                                                         @Param("statisticEndDate")Date statisticEndDate,
                                                                         @Param("managerUsername")String managerUsername);
    @Query("SELECT new com.example.fresher_manager.entity.CenterStat(c.id, c.name, c.address, COUNT(DISTINCT f.id)) " +
            "FROM Center c " +
            "JOIN Course ce ON c.id = ce.center.id " +
            "JOIN Enrollment e ON ce.id = e.course.id " +
            "JOIN Fresher f ON e.fresher.id = f.id " +
            "JOIN Management me ON c.id = me.center.id " +
            "JOIN Manager m ON me.manager.id = m.id " +
            "WHERE c.status IS TRUE " +
            "AND m.username = :managerUsername " +
            "AND me.endDate IS NULL " +
            "AND e.endDate IS NULL " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(DISTINCT f.id) DESC")
    List<CenterStat> getFresherCountByCenterWithCurrentDateAndManagerUsername(@Param("managerUsername")String managerUsername);
}