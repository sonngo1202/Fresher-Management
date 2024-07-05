package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRankRepository extends JpaRepository<Rank, Long> {
}
