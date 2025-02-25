package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Subscriber;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.UtenteGenerico;
import com.example.filiera_francoletti_belardinelli_raiola.repository.AnimatoreRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HandlerAnimatore {

    private final AnimatoreRepository animatoreRepository;
    private final HandlerNotifica notificaService; // Inietta anche il NotificaService
    private final UtenteRepository utenteRepository;


    @Autowired
    public HandlerAnimatore(AnimatoreRepository animatoreRepository, HandlerNotifica notificaService, UtenteRepository utenteRepository) {
        this.animatoreRepository = animatoreRepository;
        this.notificaService = notificaService;
        this.utenteRepository = utenteRepository;
    }

    /**
     * Iscrive un utente (subscriber) alle notifiche di un animatore.
     */
    public void subscribe(Long animatoreId, Long utenteId) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        UtenteGenerico utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + utenteId));
        // Aggiunge l'utente alla lista dei subscriber dell'animatore
        animatore.subscribe(utente);
        animatoreRepository.save(animatore);
    }

    /**
     * Crea un evento per l'animatore, notificando i subscriber.
     * Ritorna l'evento appena creato.
     */
    public Evento createEvent(Long animatoreId, String eventName, String description, int maxPeople, Indirizzo place) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        // Chiamata al metodo con cinque parametri: l'ultimo è il NotificaService
        animatore.createEvent(eventName, description, maxPeople, place, notificaService);
        animatoreRepository.save(animatore);
        List<Evento> eventi = animatore.getEventsCreated();
        return eventi.get(eventi.size() - 1);
    }

    /**
     * Recupera tutti gli eventi creati da un animatore.
     */
    public List<Evento> getEventsByAnimatore(Long animatoreId) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        return animatore.getEventsCreated();
    }

    /**
     * Recupera un evento specifico creato dall'animatore, dato il suo id.
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
     * Rimuove un evento dall'animatore.
     */
    public void removeEvent(Long animatoreId, Long eventId) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        animatore.getEventsCreated().removeIf(event -> event.getId().equals(eventId));
        animatoreRepository.save(animatore);
    }
}
