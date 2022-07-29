package com.careerdevs.muzick1.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
/* import org.springframework.data.jpa.repository.Query; */

import com.careerdevs.muzick1.models.Listener;

public interface ListenerRepo extends JpaRepository<Listener, Long> {
    //generates query: "SELECT * FROM listener WHERE age = ?1"
    List<Listener> findAllByAge(Integer age);
    Optional<Listener> findUserById(Long id);

    /* @Query("Select * FROM listener WHERE name LIKE '%?1%'")
    List<Listener> findAllByPartialName(String query); */

}
