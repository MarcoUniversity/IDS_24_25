package com.example.filiera_francoletti_belardinelli_raiola.Model;

public class Evento {

    private String name;
    private String description;
    private int maxPeople;
    private Indirizzo place;
    private static int idEvent =0;

    public Evento(String name, String description, int maxPeople, Indirizzo place) {
        this.name = name;
        this.description = description;
        this.maxPeople = maxPeople;
        this.place = place;
        this.idEvent++;
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

}


