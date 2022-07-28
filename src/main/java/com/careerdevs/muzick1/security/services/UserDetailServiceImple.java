package com.careerdevs.muzick1.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.careerdevs.muzick1.models.User;
import com.careerdevs.muzick1.repos.UserRepo;

@Service
public class UserDetailServiceImple implements UserDetailsService {
    
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));

        return (UserDetails) UserDetailsImpl.build(user);
    }


}
