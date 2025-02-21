package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerAnimatore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

import java.util.ArrayList;
import java.util.List;

public class AnimatoreDellaFiliera {
    private String name;
    private HandlerAnimatore animatorHandler;
    private List<Subscriber> subscribers;

    public AnimatoreDellaFiliera(String name, HandlerAnimatore animatorHandler) {
        this.name = name;
        this.animatorHandler = animatorHandler;
        this.subscribers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Evento> getEventsCreated() {
        //return animatorHandler.getEvents();
        return null;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public Evento createEvent(String name, String description, int maxPeople, Indirizzo place) {
        Evento event = new Evento(name, description, maxPeople, place);
        //animatorHandler.addEvent(event);
        notify();
        return event;
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

    /*public Invito sendInvite(Venditore receiver, Evento event, String description) {
        return new Invito(receiver, event, description);
    }*/

    public void removeEvent(int id) {
        //animatorHandler.removeEvent(id);
    }
}
