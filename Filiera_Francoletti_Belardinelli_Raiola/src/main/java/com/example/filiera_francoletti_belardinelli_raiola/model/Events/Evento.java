package com.example.filiera_francoletti_belardinelli_raiola.model.Events;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Date;

/**
 * Classe che rappresenta un evento nella filiera.
 * Ogni evento ha un nome, una descrizione, un numero massimo di partecipanti,
 * un luogo in cui si svolge e un animatore che lo crea.
 */
@Entity
public class Evento {

    /**
     * Identificativo univoco dell'evento, generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome dell'evento.
     */
    private String name;

    /**
     * Descrizione dell'evento.
     */
    private String description;

    /**
     * Numero massimo di persone che possono partecipare all'evento.
     */
    private int maxPeople;

    /**
     * Luogo in cui si svolge l'evento.
     */
    @Embedded
    private Indirizzo place;

    /**
     * Animatore che ha creato l'evento.
     * Questo campo non viene serializzato per evitare loop di riferimento.
     */
    @ManyToOne
    @JsonBackReference
    private AnimatoreDellaFiliera creator;

    /**
     * Data e ora di creazione dell'evento.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Evento() {}

    /**
     * Costruttore che inizializza un evento con i dati specificati.
     *
     * @param name Nome dell'evento.
     * @param description Descrizione dell'evento.
     * @param maxPeople Numero massimo di partecipanti.
     * @param place Indirizzo in cui si svolge l'evento.
     * @param creator Animatore che ha creato l'evento.
     */
    public Evento(String name, String description, int maxPeople, Indirizzo place, AnimatoreDellaFiliera creator) {
        this.name = name;
        this.description = description;
        this.maxPeople = maxPeople;
        this.place = place;
        this.creator = creator;
    }

    /**
     * Restituisce l'ID dell'evento.
     *
     * @return L'ID dell'evento.
     */
    public Long getId() { return id; }

    /**
     * Imposta l'ID dell'evento.
     *
     * @param id Nuovo ID dell'evento.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Restituisce il nome dell'evento.
     *
     * @return Il nome dell'evento.
     */
    public String getName() { return name; }

    /**
     * Imposta il nome dell'evento.
     *
     * @param name Nuovo nome dell'evento.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Restituisce la descrizione dell'evento.
     *
     * @return La descrizione dell'evento.
     */
    public String getDescription() { return description; }

    /**
     * Imposta la descrizione dell'evento.
     *
     * @param description Nuova descrizione dell'evento.
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Restituisce il numero massimo di partecipanti all'evento.
     *
     * @return Il numero massimo di partecipanti.
     */
    public int getMaxPeople() { return maxPeople; }

    /**
     * Imposta il numero massimo di partecipanti all'evento.
     *
     * @param maxPeople Nuovo numero massimo di partecipanti.
     */
    public void setMaxPeople(int maxPeople) { this.maxPeople = maxPeople; }

    /**
     * Restituisce l'indirizzo in cui si svolge l'evento.
     *
     * @return L'indirizzo dell'evento.
     */
    public Indirizzo getPlace() { return place; }

    /**
     * Imposta l'indirizzo dell'evento.
     *
     * @param place Nuovo indirizzo dell'evento.
     */
    public void setPlace(Indirizzo place) { this.place = place; }

    /**
     * Restituisce l'animatore che ha creato l'evento.
     *
     * @return L'animatore che ha creato l'evento.
     */
    public AnimatoreDellaFiliera getCreator() { return creator; }

    /**
     * Imposta l'animatore dell'evento.
     *
     * @param creator Nuovo animatore dell'evento.
     */
    public void setCreator(AnimatoreDellaFiliera creator) { this.creator = creator; }

    /**
     * Restituisce la data di creazione dell'evento.
     *
     * @return La data di creazione dell'evento.
     */
    public Date getCreatedAt() { return createdAt; }

    /**
     * Imposta la data di creazione dell'evento.
     *
     * @param createdAt Nuova data di creazione dell'evento.
     */
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
