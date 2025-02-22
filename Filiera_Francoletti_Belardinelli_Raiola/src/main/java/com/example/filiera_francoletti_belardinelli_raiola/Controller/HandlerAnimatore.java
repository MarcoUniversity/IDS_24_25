package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Events.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

import java.util.ArrayList;
import java.util.List;

public class HandlerAnimatore {
    private List<Evento> eventsCreated;

    public HandlerAnimatore(List<Evento> eventsCreated) {

        this.eventsCreated = (eventsCreated != null) ? eventsCreated : new ArrayList<>();
    }

    public List<Evento> getEventsCreated() {
        return eventsCreated;
    }

    public void setEventsCreated(List<Evento> eventsCreated) {
        this.eventsCreated = eventsCreated;
    }

    public void createEvent(Evento event) {
        if (event != null) {
            this.eventsCreated.add(event);
        }
    }

    public Evento getEventsById(int id) {
        for (Evento event : eventsCreated) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public Invito sendInvite(AnimatoreDellaFiliera sender, Venditore receiver, Evento event, String description) {
        Invito invite = new Invito(sender, receiver, event, description);
        receiver.getHandlerInvite().addInvite(invite);
        return invite;
    }

    public void removeEvent(int id) {
        eventsCreated.removeIf(event -> event.getId() == id);
    }
}
