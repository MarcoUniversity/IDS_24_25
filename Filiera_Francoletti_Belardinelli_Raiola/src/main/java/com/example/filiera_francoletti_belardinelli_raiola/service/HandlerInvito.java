package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.events.Invito;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service per la gestione degli inviti agli eventi.
 */
@Service
public class HandlerInvito {
    private List<Invito> invite;

    /**
     * Costruttore della classe HandlerInvito.
     * Inizializza la lista degli inviti.
     *
     * @param invite Lista iniziale di inviti, se null viene inizializzata come una lista vuota.
     */
    public HandlerInvito(List<Invito> invite) {
        this.invite = (invite != null) ? invite : new ArrayList<>();
    }

    /**
     * Restituisce la lista degli inviti.
     *
     * @return Lista di oggetti Invito.
     */
    public List<Invito> getInvite() {
        return invite;
    }

    /**
     * Imposta una nuova lista di inviti.
     *
     * @param invite Nuova lista di inviti, se null viene inizializzata come una lista vuota.
     */
    public void setInvite(List<Invito> invite) {
        this.invite = (invite != null) ? invite : new ArrayList<>();
    }

    /**
     * Aggiunge un invito alla lista se non è nullo.
     *
     * @param invite L'invito da aggiungere.
     */
    public void addInvite(Invito invite) {
        if (invite != null) {
            this.invite.add(invite);
        }
    }

    /**
     * Gestisce un invito dato il suo ID.
     *
     * @param id ID dell'invito da gestire.
     */
    public void manageInvite(int id) {
        for (Invito invito : invite) {
            if (invito.getId() == id) {
                // Logica per gestire l'invito
                System.out.println("Managing invite with ID: " + id);
            }
        }
    }

    /**
     * Recupera un invito specifico dato il suo ID.
     *
     * @param id ID dell'invito da cercare.
     * @return L'oggetto Invito corrispondente, oppure null se non trovato.
     */
    public Invito getInviteById(int id) {
        for (Invito invito : invite) {
            if (invito.getId() == id) {
                return invito;
            }
        }
        return null;
    }
}
