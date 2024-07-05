package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFresherRepository extends JpaRepository<Fresher, Long> {
    Fresher findByCode(String code);
    Fresher findByEmail(String email);
    Fresher findByPhone(String phone);
    Optional<Fresher> findByIdAndStatusTrue(Long id);
}
