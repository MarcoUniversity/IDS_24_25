package com.example.filiera_francoletti_belardinelli_raiola.Model.Events;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AnimatoreDellaFiliera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Evento> eventsCreated = new ArrayList<>();

    public AnimatoreDellaFiliera() {}

    public AnimatoreDellaFiliera(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Evento> getEventsCreated() { return eventsCreated; }
    public void setEventsCreated(List<Evento> eventsCreated) { this.eventsCreated = eventsCreated; }
    public void addEvent(Evento event) { eventsCreated.add(event); }
}
