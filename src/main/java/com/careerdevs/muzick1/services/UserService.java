package com.careerdevs.muzick1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.careerdevs.muzick1.models.User;
import com.careerdevs.muzick1.repos.UserRepo;
import com.careerdevs.muzick1.security.services.UserDetailsImpl;

public class UserService {
    
    @Autowired
    UserRepo userRepo;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) auth.getPrincipal();

        Optional<User> currentUser = userRepo.findById(userDetailsImpl.getId());

        if (currentUser.isEmpty()) {
            return null;
        }

        return currentUser.get();

    }

}
