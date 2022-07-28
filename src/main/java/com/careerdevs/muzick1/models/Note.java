package com.careerdevs.muzick1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "listener_id", referencedColumnName = "id") // (name = {variable_id})
    @JsonIgnoreProperties("age") // include all except listed
//    @JsonIncludeProperties({"age", "name"}) // exclude all except listed.
    private Listener listener;

    public Note() {
    }

    public Note(Long id, String title, String body, Listener listener) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.listener = listener;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Listener getListener() {
        return this.listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

}
