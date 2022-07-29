package com.careerdevs.muzick1.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.careerdevs.muzick1.models.ERole;
import com.careerdevs.muzick1.models.Role;
import com.careerdevs.muzick1.models.User;
import com.careerdevs.muzick1.payloads.request.LogInRequest;
import com.careerdevs.muzick1.payloads.request.SignUpRequest;
import com.careerdevs.muzick1.payloads.response.JwtResponse;
import com.careerdevs.muzick1.payloads.response.Message;
import com.careerdevs.muzick1.repos.RoleRepo;
import com.careerdevs.muzick1.repos.UserRepo;
import com.careerdevs.muzick1.security.jwt.JwtUtils;
import com.careerdevs.muzick1.security.services.UserDetailsImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> authentication(@RequestBody LogInRequest logInRequest) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(logInRequest.getUsername(), logInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtUtils.generateJwtToken(auth);

        UserDetails userDetails = (UserDetailsImpl) auth.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registration(@RequestBody SignUpRequest signUpRequest) {
        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new Message("Error, Username Exists"));
        }

        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        Set<String> strRoles = signUpRequest.getRoles();

        if (strRoles == null) {
            Role userRole = roleRepo.findByName(ERole.ROLE_USER).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                    case "administrator":
                        Role userRole = roleRepo.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
                        roles.add(adminRole);
                    break;
                    
                    case "mod":
                    case "moderator":
                        Role userRoles1 = roleRepo.findByName(ERole.ROLE_MOD).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
                        roles.add(modRole);
                        break;
                    
                    default:
                        Role userRoles2 = roleRepo.findByName(ERole.ROLE_USER).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
                        roles.add(userRole);
                    break;
                }
            });
        }

        user.setRoles(roles);
        userRepo.save(user);

        return new ResponseEntity<>(new Message("User Created"), HttpStatus.CREATED);
    }

}
