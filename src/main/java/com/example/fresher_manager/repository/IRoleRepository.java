package com.example.fresher_manager.repository;

import com.example.fresher_manager.entity.Role;
import com.example.fresher_manager.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}
