package com.careerdevs.muzick1.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.careerdevs.muzick1.models.User;

public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
