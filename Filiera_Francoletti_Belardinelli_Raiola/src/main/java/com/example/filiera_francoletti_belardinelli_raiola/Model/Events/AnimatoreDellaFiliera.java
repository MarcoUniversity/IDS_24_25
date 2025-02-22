package com.example.filiera_francoletti_belardinelli_raiola.Model.Events;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerAnimatore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Users.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class AnimatoreDellaFiliera {
    private String name;
    private HandlerAnimatore animatorHandler;
    private List<Subscriber> subscribers;

    public AnimatoreDellaFiliera(String name) {
        this.name = name;
        this.animatorHandler = new HandlerAnimatore(new ArrayList<>());
        this.subscribers = new ArrayList<Subscriber>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Evento> getEventsCreated() {
        return this.animatorHandler.getEventsCreated();
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = (subscribers != null) ? subscribers : new ArrayList<>();
    }

    public void createEvent(String name, String description, int maxPeople, Indirizzo place) {
        Evento event = new Evento(name, description, maxPeople, place,this);
        this.animatorHandler.createEvent(event);
        notify();
    }

    public void subscribe(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    public void sendInvite(Venditore receiver, Evento event, String description) {
        this.animatorHandler.sendInvite(this, receiver, event, description);
    }

    public void removeEvent(int id) {
        this.animatorHandler.removeEvent(id);
    }
}
