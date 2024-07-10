package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Fresher;
import com.example.fresher_manager.entity.ScoreStat;
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

//    @Query("SELECT new com.example.entity.ScoreStat(score.scoreRange, COUNT(f)) " +
//            "FROM (" +
//            "    SELECT '0-4' AS scoreRange, 0 AS firstScore, 4 AS secondScore UNION ALL " +
//            "    SELECT '4-6' AS scoreRange, 4 AS firstScore, 6 AS secondScore UNION ALL " +
//            "    SELECT '6-8' AS scoreRange, 6 AS firstScore, 8 AS secondScore UNION ALL " +
//            "    SELECT '8-9' AS scoreRange, 8 AS firstScore, 9 AS secondScore UNION ALL " +
//            "    SELECT '9-10' AS scoreRange, 9 AS firstScore, 10 AS secondScore " +
//            ") score ")
//    List<ScoreStat> getFresherCountByScore();
}
