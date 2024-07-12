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

    @Query("SELECT f FROM Fresher f JOIN f.enrollments e JOIN e.course c JOIN c.center ct JOIN ct.managements mm JOIN mm.manager m WHERE m.username = :managerUsername  AND mm.endDate IS NULL AND e.endDate IS NULL")
    List<Fresher> findByManagerUsername(@Param("managerUsername") String managerUsername);

    @Query(value = "SELECT " +
            "CASE " +
            "    WHEN avg_score >= 0 AND avg_score < 4 THEN '0-4' " +
            "    WHEN avg_score >= 4 AND avg_score < 6 THEN '4-6' " +
            "    WHEN avg_score >= 6 AND avg_score < 8 THEN '6-8' " +
            "    WHEN avg_score >= 8 AND avg_score < 9 THEN '8-9' " +
            "    WHEN avg_score >= 9 AND avg_score <= 10 THEN '9-10' " +
            "END AS score_range, COUNT(*) AS count " +
            "FROM ( " +
            "    SELECT f.id, AVG(r.score) AS avg_score " +
            "    FROM fresher f " +
            "    JOIN result r ON r.fresher_id = f.id " +
            "    GROUP BY f.id " +
            "    HAVING COUNT(r.id) >= 3 " +
            ") AS avg_scores " +
            "GROUP BY " +
            "CASE " +
            "    WHEN avg_score >= 0 AND avg_score < 4 THEN '0-4' " +
            "    WHEN avg_score >= 4 AND avg_score < 6 THEN '4-6' " +
            "    WHEN avg_score >= 6 AND avg_score < 8 THEN '6-8' " +
            "    WHEN avg_score >= 8 AND avg_score < 9 THEN '8-9' " +
            "    WHEN avg_score >= 9 AND avg_score <= 10 THEN '9-10' " +
            "END", nativeQuery = true)
    List<Object[]> getFresherCountByScoreRanges();


    @Query(value = "SELECT " +
            "CASE " +
            "    WHEN avg_score >= 0 AND avg_score < 4 THEN '0-4' " +
            "    WHEN avg_score >= 4 AND avg_score < 6 THEN '4-6' " +
            "    WHEN avg_score >= 6 AND avg_score < 8 THEN '6-8' " +
            "    WHEN avg_score >= 8 AND avg_score < 9 THEN '8-9' " +
            "    WHEN avg_score >= 9 AND avg_score <= 10 THEN '9-10' " +
            "END AS score_range, COUNT(*) AS count " +
            "FROM ( " +
            "    SELECT f.id, AVG(r.score) AS avg_score " +
            "    FROM fresher f " +
            "    JOIN result r ON r.fresher_id = f.id " +
            "    JOIN enrollment e ON e.fresher_id = f.id " +
            "    JOIN course c ON c.id = e.course_id " +
            "    JOIN center ce ON ce.id = c.center_id " +
            "    JOIN management me ON me.center_id = ce.id " +
            "    JOIN manager m ON m.id = me.manager_id " +
            "    JOIN user u ON u.id = m.id " +
            "    WHERE u.username = :managerUsername " +
            "    GROUP BY f.id " +
            "    HAVING COUNT(r.id) >= 3 " +
            ") AS avg_scores " +
            "GROUP BY " +
            "CASE " +
            "    WHEN avg_score >= 0 AND avg_score < 4 THEN '0-4' " +
            "    WHEN avg_score >= 4 AND avg_score < 6 THEN '4-6' " +
            "    WHEN avg_score >= 6 AND avg_score < 8 THEN '6-8' " +
            "    WHEN avg_score >= 8 AND avg_score < 9 THEN '8-9' " +
            "    WHEN avg_score >= 9 AND avg_score <= 10 THEN '9-10' " +
            "END", nativeQuery = true)
    List<Object[]> getFresherCountByScoreRangesAndManagerUsername(@Param("managerUsername")String managerUsername);
}
