package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

import java.util.ArrayList;
import java.util.List;

public class HandlerAnimatore {
    private List<Evento> eventsCreated;

    public HandlerAnimatore() {
        this.eventsCreated = new ArrayList<>();
    }

    public List<Evento> getEventsCreated() {
        return eventsCreated;
    }

    public void setEventsCreated(List<Evento> eventsCreated) {
        this.eventsCreated = eventsCreated;
    }

    public void createEvent(Evento event) {
        this.eventsCreated.add(event);
    }

    public Evento getEventsById(int id) {
        for (Evento event : eventsCreated) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public Invito sendInvite(Venditore receiver, Evento event, String description) {
        //return new Invito(receiver, event, description);
        return null;
    }

    public void removeEvent(int id) {
        eventsCreated.removeIf(event -> event.getId() == id);
    }
}
