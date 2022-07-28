package com.careerdevs.muzick1.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerdevs.muzick1.models.ERole;
import com.careerdevs.muzick1.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);

}
