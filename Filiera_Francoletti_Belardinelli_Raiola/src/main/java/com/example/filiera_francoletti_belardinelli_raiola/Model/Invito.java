package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

public class Invito {
    private AnimatoreDellaFiliera sender;
    private Venditore receiver;
    private Evento event;
    private String description;
    private boolean accepted;
    private static int idInvite =0;

    public Invito(AnimatoreDellaFiliera sender, Venditore receiver, Evento event, String description) {
        this.sender = sender;
        this.receiver = receiver;
        this.event = event;
        this.description = description;
        this.accepted = false;
        this.idInvite++;
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

    public int getId() {
        return idInvite;
    }

}

