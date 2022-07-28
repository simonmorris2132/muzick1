package com.careerdevs.muzick1.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.careerdevs.muzick1.models.Note;

public interface NoteRepo extends JpaRepository<Note, Long> {
    List<Note> findAllByListener_id(Long listener_id);
}
