package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Administration.Piattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
            Piattaforma pf=Piattaforma.getPlatform();
            pf.addEventInPlatform(event);
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

   /* public Invito sendInvite(AnimatoreDellaFiliera sender, Venditore receiver, Evento event, String description) {
        Invito invite = new Invito(sender, receiver, event, description);
        receiver.getHandlerInvite().addInvite(invite);
        return invite;
    }*/

    public void removeEvent(int id) {
        eventsCreated.removeIf(event -> event.getId() == id);
    }
}
