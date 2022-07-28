package com.careerdevs.muzick1.controllers;

import java.util.List;
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
import com.careerdevs.muzick1.models.Note;
import com.careerdevs.muzick1.repos.ListenerRepo;
import com.careerdevs.muzick1.repos.NoteRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/notes")
public class NoteController {
    
    @Autowired
    private NoteRepo noteRepo;

    @Autowired
    private ListenerRepo listenerRepo;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute() {
        return new ResponseEntity<>("note route", HttpStatus.OK);
    }

    @PostMapping("/{listenerId}")
    public ResponseEntity<?> createNote(@PathVariable Long listenerId, @RequestBody Note newNote) {
        Listener listener = listenerRepo.findById(listenerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        newNote.setListener(listener);

        Note note = noteRepo.save(newNote);
        return new ResponseEntity<>(note, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteRepo.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteByID(@PathVariable Long id) {
        Note note = noteRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @GetMapping("/listener/{listenerId}")
    public ResponseEntity<List<Note>> getNotesByListener(@PathVariable Long listenerId) {
        List<Note> notes = noteRepo.findAllByListener_id(listenerId);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

}
