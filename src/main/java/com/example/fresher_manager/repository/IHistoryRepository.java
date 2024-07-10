package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryRepository extends JpaRepository<History, Long> {
}