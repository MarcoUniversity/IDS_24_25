package com.example.filiera_francoletti_belardinelli_raiola.model.Events;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Subscriber;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.UtenteGenerico;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerAnimatore;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerNotifica;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AnimatoreDellaFiliera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference  // lato "gestito"
    private List<Evento> eventsCreated = new ArrayList<>();

    @Transient
    private List<Subscriber> subscribers = new ArrayList<>();

    public AnimatoreDellaFiliera() {}

    public AnimatoreDellaFiliera(String name) {
        this.name = name;
    }

    // Il metodo createEvent che notifica i subscriber
    public void createEvent(String eventName, String description, int maxPeople, Indirizzo place, HandlerNotifica notificaService) {
        Evento event = new Evento(eventName, description, maxPeople, place, this);
        this.eventsCreated.add(event);
        notifySubscribers(event, notificaService);
    }

    private void notifySubscribers(Evento event, HandlerNotifica notificaService) {
        for (Subscriber s : subscribers) {
            Long subscriberId = ((UtenteGenerico) s).getId();
            String message = "Nuovo evento: " + event.getName() + " creato dall'animatore " + this.name;
            notificaService.creaNotifica(message, subscriberId);
            s.update();
        }
    }

    public void subscribe(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Evento> getEventsCreated() { return eventsCreated; }
    public void setEventsCreated(List<Evento> eventsCreated) { this.eventsCreated = eventsCreated; }
    public List<Subscriber> getSubscribers() { return subscribers; }
    public void setSubscribers(List<Subscriber> subscribers) { this.subscribers = subscribers; }
}
