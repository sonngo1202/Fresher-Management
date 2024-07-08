package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResultRepository extends JpaRepository<Result, Long> {
}
