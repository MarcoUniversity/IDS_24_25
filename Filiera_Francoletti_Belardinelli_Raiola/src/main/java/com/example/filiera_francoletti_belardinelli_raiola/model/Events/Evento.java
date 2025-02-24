package com.example.filiera_francoletti_belardinelli_raiola.model.Events;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int maxPeople;

    @Embedded
    private Indirizzo place;

    @ManyToOne
    private AnimatoreDellaFiliera creator;

    public Evento() {}

    public Evento(String name, String description, int maxPeople, Indirizzo place, AnimatoreDellaFiliera creator) {
        this.name = name;
        this.description = description;
        this.maxPeople = maxPeople;
        this.place = place;
        this.creator = creator;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getMaxPeople() { return maxPeople; }
    public void setMaxPeople(int maxPeople) { this.maxPeople = maxPeople; }
    public Indirizzo getPlace() { return place; }
    public void setPlace(Indirizzo place) { this.place = place; }
    public AnimatoreDellaFiliera getCreator() { return creator; }
    public void setCreator(AnimatoreDellaFiliera creator) { this.creator = creator; }
}
