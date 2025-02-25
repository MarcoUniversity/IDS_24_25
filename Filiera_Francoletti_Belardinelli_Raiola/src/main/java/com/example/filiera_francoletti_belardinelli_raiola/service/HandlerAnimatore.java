package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.model.events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.users.UtenteGenerico;
import com.example.filiera_francoletti_belardinelli_raiola.repository.AnimatoreRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service per la gestione degli animatori della filiera, delle iscrizioni e degli eventi da loro creati.
 */
@Service
public class HandlerAnimatore {

    private final AnimatoreRepository animatoreRepository;
    private final HandlerNotifica notificaService;
    private final UtenteRepository utenteRepository;

    /**
     * Costruttore per inizializzare il repository degli animatori, il servizio di notifiche e il repository degli utenti.
     *
     * @param animatoreRepository repository per accedere agli animatori.
     * @param notificaService servizio per la gestione delle notifiche.
     * @param utenteRepository repository per accedere agli utenti.
     */
    @Autowired
    public HandlerAnimatore(AnimatoreRepository animatoreRepository, HandlerNotifica notificaService, UtenteRepository utenteRepository) {
        this.animatoreRepository = animatoreRepository;
        this.notificaService = notificaService;
        this.utenteRepository = utenteRepository;
    }

    /**
     * Iscrive un utente alle notifiche di un animatore.
     *
     * @param animatoreId ID dell'animatore.
     * @param utenteId ID dell'utente da iscrivere.
     * @throws RuntimeException se l'animatore o l'utente non vengono trovati.
     */
    public void subscribe(Long animatoreId, Long utenteId) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        UtenteGenerico utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + utenteId));
        animatore.subscribe(utente);
        animatoreRepository.save(animatore);
    }

    /**
     * Crea un evento per l'animatore e notifica i subscriber.
     *
     * @param animatoreId ID dell'animatore che crea l'evento.
     * @param eventName Nome dell'evento.
     * @param description Descrizione dell'evento.
     * @param maxPeople Numero massimo di partecipanti.
     * @param place Indirizzo dove si svolgerà l'evento.
     * @return L'evento creato.
     * @throws RuntimeException se l'animatore non viene trovato.
     */
    public Evento createEvent(Long animatoreId, String eventName, String description, int maxPeople, Indirizzo place) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        animatore.createEvent(eventName, description, maxPeople, place, notificaService);
        animatoreRepository.save(animatore);
        List<Evento> eventi = animatore.getEventsCreated();
        return eventi.get(eventi.size() - 1);
    }

    /**
     * Recupera tutti gli eventi creati da un determinato animatore.
     *
     * @param animatoreId ID dell'animatore.
     * @return Lista di eventi creati dall'animatore.
     * @throws RuntimeException se l'animatore non viene trovato.
     */
    public List<Evento> getEventsByAnimatore(Long animatoreId) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        return animatore.getEventsCreated();
    }

    /**
     * Recupera un evento specifico creato da un animatore.
     *
     * @param animatoreId ID dell'animatore.
     * @param eventId ID dell'evento da recuperare.
     * @return L'evento corrispondente all'ID specificato.
     * @throws RuntimeException se l'animatore o l'evento non vengono trovati.
     */
    public Evento getEventById(Long animatoreId, Long eventId) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        return animatore.getEventsCreated().stream()
                .filter(event -> event.getId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Evento non trovato con id: " + eventId));
    }

    /**
     * Rimuove un evento creato da un animatore.
     *
     * @param animatoreId ID dell'animatore.
     * @param eventId ID dell'evento da rimuovere.
     * @throws RuntimeException se l'animatore non viene trovato.
     */
    public void removeEvent(Long animatoreId, Long eventId) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        animatore.getEventsCreated().removeIf(event -> event.getId().equals(eventId));
        animatoreRepository.save(animatore);
    }
}
