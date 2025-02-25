package com.example.filiera_francoletti_belardinelli_raiola.model.Events;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Subscriber;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.UtenteGenerico;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerNotifica;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un Animatore della Filiera.
 * L'animatore può creare eventi e gestire una lista di iscritti (subscribers)
 * che verranno notificati quando un nuovo evento viene creato.
 */
@Entity
public class AnimatoreDellaFiliera {

    /**
     * Identificativo univoco dell'animatore, generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome dell'animatore della filiera.
     */
    private String name;

    /**
     * Lista degli eventi creati dall'animatore.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference  // lato "gestito"
    private List<Evento> eventsCreated = new ArrayList<>();

    /**
     * Lista degli iscritti all'animatore, che riceveranno notifiche sugli eventi.
     * Questo campo non viene persistito nel database.
     */
    @Transient
    private List<Subscriber> subscribers = new ArrayList<>();

    /**
     * Costruttore di default richiesto da JPA.
     */
    public AnimatoreDellaFiliera() {}

    /**
     * Costruttore che inizializza un animatore con un nome specificato.
     *
     * @param name Nome dell'animatore.
     */
    public AnimatoreDellaFiliera(String name) {
        this.name = name;
    }

    /**
     * Crea un nuovo evento e notifica tutti gli iscritti.
     *
     * @param eventName    Nome dell'evento.
     * @param description  Descrizione dell'evento.
     * @param maxPeople    Numero massimo di partecipanti.
     * @param place        Indirizzo in cui si svolge l'evento.
     * @param notificaService Servizio per la gestione delle notifiche.
     */
    public void createEvent(String eventName, String description, int maxPeople, Indirizzo place, HandlerNotifica notificaService) {
        Evento event = new Evento(eventName, description, maxPeople, place, this);
        this.eventsCreated.add(event);
        notifySubscribers(event, notificaService);
    }

    /**
     * Notifica tutti gli iscritti alla creazione di un nuovo evento.
     *
     * @param event          Evento appena creato.
     * @param notificaService Servizio di gestione delle notifiche.
     */
    private void notifySubscribers(Evento event, HandlerNotifica notificaService) {
        for (Subscriber s : subscribers) {
            Long subscriberId = ((UtenteGenerico) s).getId();
            String message = "Nuovo evento: " + event.getName() + " creato dall'animatore " + this.name;
            notificaService.creaNotifica(message, subscriberId);
            s.update();
        }
    }

    /**
     * Aggiunge un utente alla lista degli iscritti per ricevere notifiche.
     *
     * @param subscriber L'utente da iscrivere.
     */
    public void subscribe(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    /**
     * Rimuove un utente dalla lista degli iscritti.
     *
     * @param subscriber L'utente da disiscrivere.
     */
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Restituisce l'ID dell'animatore.
     *
     * @return L'ID dell'animatore.
     */
    public Long getId() { return id; }

    /**
     * Imposta l'ID dell'animatore.
     *
     * @param id Nuovo ID dell'animatore.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Restituisce il nome dell'animatore.
     *
     * @return Il nome dell'animatore.
     */
    public String getName() { return name; }

    /**
     * Imposta il nome dell'animatore.
     *
     * @param name Nuovo nome dell'animatore.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Restituisce la lista degli eventi creati dall'animatore.
     *
     * @return Lista degli eventi creati.
     */
    public List<Evento> getEventsCreated() { return eventsCreated; }

    /**
     * Imposta la lista degli eventi creati dall'animatore.
     *
     * @param eventsCreated Nuova lista di eventi.
     */
    public void setEventsCreated(List<Evento> eventsCreated) { this.eventsCreated = eventsCreated; }

    /**
     * Restituisce la lista degli iscritti all'animatore.
     *
     * @return Lista degli iscritti.
     */
    public List<Subscriber> getSubscribers() { return subscribers; }

    /**
     * Imposta la lista degli iscritti all'animatore.
     *
     * @param subscribers Nuova lista di iscritti.
     */
    public void setSubscribers(List<Subscriber> subscribers) { this.subscribers = subscribers; }
}
