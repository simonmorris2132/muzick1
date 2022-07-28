package com.careerdevs.muzick1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.careerdevs.muzick1.security.jwt.AuthEntryPointJwt;
import com.careerdevs.muzick1.security.services.UserDetailServiceImple;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailServiceImple userDetailService;

    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

}
