package com.ferhad.excellentjobs.repository;

import com.ferhad.excellentjobs.model.Role;
import com.ferhad.excellentjobs.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole role);
}
