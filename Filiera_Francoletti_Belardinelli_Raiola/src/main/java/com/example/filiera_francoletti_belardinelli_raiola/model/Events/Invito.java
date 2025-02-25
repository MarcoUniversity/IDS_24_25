package com.example.filiera_francoletti_belardinelli_raiola.model.Events;

import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import jakarta.persistence.*;

/**
 * Classe che rappresenta un invito a un evento.
 * Un invito viene inviato da un Animatore della Filiera a un Venditore per partecipare a un evento.
 */
@Entity
public class Invito {

    /**
     * Identificativo univoco dell'invito, generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * L'animatore che ha inviato l'invito.
     */
    @ManyToOne
    private AnimatoreDellaFiliera sender;

    /**
     * Il venditore che riceve l'invito.
     */
    @ManyToOne
    private Venditore receiver;

    /**
     * L'evento per cui l'invito è stato inviato.
     */
    @ManyToOne
    private Evento event;

    /**
     * Descrizione dell'invito.
     */
    private String description;

    /**
     * Stato di accettazione dell'invito.
     */
    private boolean accepted;

    /**
     * Costruttore che inizializza un invito con i parametri specificati.
     *
     * @param sender Animatore che invia l'invito.
     * @param receiver Venditore destinatario dell'invito.
     * @param event Evento a cui si riferisce l'invito.
     * @param description Descrizione dell'invito.
     */
    public Invito(AnimatoreDellaFiliera sender, Venditore receiver, Evento event, String description) {
        this.sender = sender;
        this.receiver = receiver;
        this.event = event;
        this.description = description;
        this.accepted = false;
    }

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Invito() {}

    /**
     * Restituisce l'animatore che ha inviato l'invito.
     *
     * @return L'animatore che ha inviato l'invito.
     */
    public AnimatoreDellaFiliera getSender() {
        return sender;
    }

    /**
     * Imposta l'animatore che ha inviato l'invito.
     *
     * @param sender Nuovo animatore mittente dell'invito.
     */
    public void setSender(AnimatoreDellaFiliera sender) {
        this.sender = sender;
    }

    /**
     * Restituisce il venditore che ha ricevuto l'invito.
     *
     * @return Il venditore destinatario dell'invito.
     */
    public Venditore getReceiver() {
        return receiver;
    }

    /**
     * Imposta il venditore che ha ricevuto l'invito.
     *
     * @param receiver Nuovo venditore destinatario dell'invito.
     */
    public void setReceiver(Venditore receiver) {
        this.receiver = receiver;
    }

    /**
     * Restituisce l'evento associato all'invito.
     *
     * @return L'evento a cui si riferisce l'invito.
     */
    public Evento getEvent() {
        return event;
    }

    /**
     * Imposta l'evento associato all'invito.
     *
     * @param event Nuovo evento a cui si riferisce l'invito.
     */
    public void setEvent(Evento event) {
        this.event = event;
    }

    /**
     * Restituisce la descrizione dell'invito.
     *
     * @return La descrizione dell'invito.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione dell'invito.
     *
     * @param description Nuova descrizione dell'invito.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Restituisce lo stato di accettazione dell'invito.
     *
     * @return true se l'invito è stato accettato, false altrimenti.
     */
    public boolean isAccepted() {
        return accepted;
    }

    /**
     * Imposta lo stato di accettazione dell'invito.
     *
     * @param accepted Nuovo stato di accettazione dell'invito.
     */
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    /**
     * Restituisce l'ID dell'invito.
     *
     * @return L'ID univoco dell'invito.
     */
    public Long getId() {
        return id;
    }
}
