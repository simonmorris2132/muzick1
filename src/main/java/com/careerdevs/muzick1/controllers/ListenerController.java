package com.careerdevs.muzick1.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.careerdevs.muzick1.models.Listener;
import com.careerdevs.muzick1.models.User;
import com.careerdevs.muzick1.repos.ListenerRepo;
import com.careerdevs.muzick1.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/listeners")
public class ListenerController {
    
    @Autowired
    private ListenerRepo repo;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> rootRoute() {
       return new ResponseEntity<>("Okay", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createListener(@RequestBody Listener newListener) {
        
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        newListener.setUser(currentUser);

        Listener listener = repo.save(newListener);

        return new ResponseEntity<>(listener, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllListeners() {
        java.util.List<Listener> listeners = repo.findAll(); 

        return new ResponseEntity<>(listeners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneListener(@PathVariable Long id) {
//        repo.findById(id).orElseThrow(() -> new ResponseEntity(HttpStatus.NOT_FOUND));

        Optional<Listener> maybeListener = repo.findById(id);

        if (maybeListener.isEmpty()) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(maybeListener.get(), HttpStatus.OK);

    }

}
