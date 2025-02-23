package com.example.filiera_francoletti_belardinelli_raiola.Model.Events;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;
import jakarta.persistence.*;

@Entity
public class Invito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AnimatoreDellaFiliera sender;

    @ManyToOne
    private Venditore receiver;

    @ManyToOne
    private Evento event;

    private String description;
    private boolean accepted;

    public Invito(AnimatoreDellaFiliera sender, Venditore receiver, Evento event, String description) {
        this.sender = sender;
        this.receiver = receiver;
        this.event = event;
        this.description = description;
        this.accepted = false;
    }

    public Invito() {

    }

    public AnimatoreDellaFiliera getSender() {
        return sender;
    }

    public void setSender(AnimatoreDellaFiliera sender) {
        this.sender = sender;
    }

    public Venditore getReceiver() {
        return receiver;
    }

    public void setReceiver(Venditore receiver) {
        this.receiver = receiver;
    }

    public Evento getEvent() {
        return event;
    }

    public void setEvent(Evento event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Long getId() {
        return id;
    }

}

