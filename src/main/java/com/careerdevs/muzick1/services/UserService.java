package com.careerdevs.muzick1.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.careerdevs.muzick1.repos.UserRepo;

public class UserService {
    
    @Autowired
    UserRepo userRepo;

}
