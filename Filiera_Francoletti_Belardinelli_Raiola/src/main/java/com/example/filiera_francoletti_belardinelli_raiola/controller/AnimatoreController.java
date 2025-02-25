package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.UtenteGenerico;
import com.example.filiera_francoletti_belardinelli_raiola.repository.AnimatoreRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.EventoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.UtenteRepository;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerAnimatore;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerNotifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per gestire le operazioni sugli Animatori della Filiera.
 * Fornisce API per creare, leggere, aggiornare ed eliminare animatori e i loro eventi.
 */
@RestController
@RequestMapping("/api/v1/animatori")
public class AnimatoreController {

    private final AnimatoreRepository animatoreRepository;
    private final EventoRepository eventoRepository;
    private final HandlerNotifica notificaService;
    private final HandlerAnimatore handlerAnimatore;
    private final UtenteRepository utenteRepository;

    /**
     * Costruttore per l'iniezione delle dipendenze necessarie.
     *
     * @param utenteRepository repository degli utenti generici
     * @param handlerAnimatore servizio per la gestione degli animatori
     * @param animatoreRepository repository degli animatori
     * @param eventoRepository repository degli eventi
     * @param notificaService servizio per la gestione delle notifiche
     */
    @Autowired
    public AnimatoreController(UtenteRepository utenteRepository, HandlerAnimatore handlerAnimatore, AnimatoreRepository animatoreRepository, EventoRepository eventoRepository, HandlerNotifica notificaService) {
        this.animatoreRepository = animatoreRepository;
        this.eventoRepository = eventoRepository;
        this.notificaService = notificaService;
        this.handlerAnimatore = handlerAnimatore;
        this.utenteRepository = utenteRepository;
    }

    /**
     * Crea un nuovo animatore.
     *
     * @param animatore l'oggetto AnimatoreDellaFiliera da salvare
     * @return ResponseEntity contenente l'animatore salvato con stato HTTP 201 CREATED
     */
    @PostMapping
    public ResponseEntity<AnimatoreDellaFiliera> createAnimatore(@RequestBody AnimatoreDellaFiliera animatore) {
        AnimatoreDellaFiliera saved = this.animatoreRepository.save(animatore);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Iscrive un utente alle notifiche di un animatore.
     *
     * @param animatoreId ID dell'animatore
     * @param utenteId ID dell'utente
     * @return ResponseEntity con un messaggio di conferma
     */
    @PostMapping("/{animatoreId}/subscribe/{utenteId}")
    public ResponseEntity<String> subscribeToAnimatore(@PathVariable Long animatoreId, @PathVariable Long utenteId) {
        try {
            this.handlerAnimatore.subscribe(animatoreId, utenteId);
            return ResponseEntity.ok("Utente con id " + utenteId + " iscritto alle notifiche dell'animatore " + animatoreId);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    /**
     * Disiscrive un utente dalle notifiche di un animatore.
     *
     * @param animatoreId ID dell'animatore
     * @param utenteId ID dell'utente
     * @return ResponseEntity con un messaggio di conferma
     */
    @PostMapping("/{animatoreId}/unsubscribe/{utenteId}")
    public ResponseEntity<String> unsubscribeFromAnimatore(@PathVariable Long animatoreId, @PathVariable Long utenteId) {
        AnimatoreDellaFiliera animatore = this.animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        UtenteGenerico utente = this.utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + utenteId));

        animatore.unsubscribe(utente);
        this.animatoreRepository.save(animatore);

        return ResponseEntity.ok("Utente disiscritto con successo dall'animatore " + animatoreId);
    }

    /**
     * Recupera tutti gli animatori.
     *
     * @return ResponseEntity contenente la lista di tutti gli animatori
     */
    @GetMapping
    public ResponseEntity<List<AnimatoreDellaFiliera>> getAllAnimatori() {
        List<AnimatoreDellaFiliera> animatori = this.animatoreRepository.findAll();
        return ResponseEntity.ok(animatori);
    }

    /**
     * Recupera un animatore per ID.
     *
     * @param id ID dell'animatore
     * @return ResponseEntity contenente l'animatore trovato
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnimatoreDellaFiliera> getAnimatoreById(@PathVariable Long id) {
        return animatoreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un evento per un animatore e notifica i subscriber.
     *
     * @param animatoreId ID dell'animatore
     * @param evento l'evento da creare
     * @return ResponseEntity contenente l'evento creato
     */
    @PostMapping("/{animatoreId}/eventi")
    public ResponseEntity<Evento> createEvento(@PathVariable Long animatoreId, @RequestBody Evento evento) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        evento.setCreator(animatore);
        Evento savedEvento = this.eventoRepository.save(evento);
        animatore.getEventsCreated().add(savedEvento);
        animatore.createEvent(evento.getName(), evento.getDescription(), evento.getMaxPeople(), evento.getPlace(), this.notificaService);
        this.animatoreRepository.save(animatore);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }
}
