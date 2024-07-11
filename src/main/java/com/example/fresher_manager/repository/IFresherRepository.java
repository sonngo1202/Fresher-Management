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

    @Query("SELECT f FROM Fresher f JOIN f.enrollments e JOIN e.course c JOIN c.center ct JOIN ct.managements mm JOIN mm.manager m WHERE m.username = :managerUsername AND mm.endDate IS NULL AND e.endDate IS NULL")
    List<Fresher> findByManagerUsername(@Param("managerUsername") String managerUsername);

    @Query(value = "SELECT COUNT(*) " +
            "FROM ( " +
            "    SELECT f.id, AVG(r.score) AS avg_score " +
            "    FROM fresher f " +
            "    JOIN result r ON r.fresher_id = f.id " +
            "    GROUP BY f.id " +
            "    HAVING COUNT(r.id) >= 3 " +
            ") AS avg_scores " +
            "WHERE avg_score >= :firstScore AND avg_score < :secondScore", nativeQuery = true)
    long getFresherCountByScore(@Param("firstScore") float firstScore,
                                @Param("secondScore") float secondScore);

}
