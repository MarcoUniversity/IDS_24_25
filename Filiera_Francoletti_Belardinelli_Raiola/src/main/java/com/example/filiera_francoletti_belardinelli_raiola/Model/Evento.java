package com.example.filiera_francoletti_belardinelli_raiola.Model;

public class Evento {

    private String name;
    private String description;
    private int maxPeople;
    private Indirizzo place;
    private AnimatoreDellaFiliera creator;
    private static int nextIdEvent =0;
    private int idEvent;

    public Evento(String name, String description, int maxPeople, Indirizzo place,AnimatoreDellaFiliera creator) {
        this.name = name;
        this.description = description;
        this.maxPeople = maxPeople;
        this.place = place;
        this.creator = creator;
        this.idEvent=++nextIdEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Indirizzo getPlace() {
        return place;
    }

    public void setPlace(Indirizzo place) {
        this.place = place;
    }

    public int getId() {
        return idEvent;
    }

    public AnimatoreDellaFiliera getCreator() {
        return creator;
    }

    public void setCreator(AnimatoreDellaFiliera creator) {
        this.creator = creator;
    }
}


