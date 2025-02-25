package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.users.Notifica;
import com.example.filiera_francoletti_belardinelli_raiola.repository.NotificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service per la gestione delle notifiche degli utenti.
 */
@Service
public class HandlerNotifica {

    private final NotificaRepository notificaRepository;

    /**
     * Costruttore per iniettare il repository delle notifiche.
     *
     * @param notificaRepository il repository delle notifiche.
     */
    @Autowired
    public HandlerNotifica(NotificaRepository notificaRepository) {
        this.notificaRepository = notificaRepository;
    }

    /**
     * Crea una nuova notifica per un utente specifico.
     *
     * @param messaggio Il contenuto della notifica.
     * @param utenteId L'ID dell'utente destinatario della notifica.
     * @return L'oggetto Notifica creato e salvato nel database.
     */
    public Notifica creaNotifica(String messaggio, Long utenteId) {
        Notifica notifica = new Notifica(messaggio, utenteId);
        System.out.println("Creazione notifica per utente " + utenteId + ": " + messaggio);
        return notificaRepository.save(notifica);
    }

    /**
     * Recupera tutte le notifiche per un determinato utente.
     *
     * @param utenteId L'ID dell'utente per il quale recuperare le notifiche.
     * @return Una lista di oggetti Notifica associati all'utente specificato.
     */
    public List<Notifica> getNotifichePerUtente(Long utenteId) {
        return notificaRepository.findByUtenteId(utenteId);
    }
}
