package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.repository.AnimatoreRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.EventoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerNotifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione degli eventi.
 * Fornisce API REST per la creazione, modifica, eliminazione e recupero di eventi.
 */
@RestController
@RequestMapping("/api/v1/eventi")
public class EventoController {

    private final EventoRepository eventoRepository;
    private final AnimatoreRepository animatoreRepository;
    private final HandlerNotifica notificaService;

    /**
     * Costruttore per l'iniezione delle dipendenze.
     *
     * @param eventoRepository Repository per la gestione degli eventi
     * @param animatoreRepository Repository per la gestione degli animatori
     * @param notificaService Servizio per la gestione delle notifiche
     */
    @Autowired
    public EventoController(EventoRepository eventoRepository,
                            AnimatoreRepository animatoreRepository,
                            HandlerNotifica notificaService) {
        this.eventoRepository = eventoRepository;
        this.animatoreRepository = animatoreRepository;
        this.notificaService = notificaService;
    }

    /**
     * Crea un nuovo evento e notifica i subscriber dell'animatore.
     *
     * @param evento Evento da creare
     * @return ResponseEntity con l'evento creato o un codice di errore
     */
    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        if (evento.getCreator() == null || evento.getCreator().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        AnimatoreDellaFiliera animatore = animatoreRepository.findById(evento.getCreator().getId())
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + evento.getCreator().getId()));

        evento.setCreator(animatore);
        animatore.createEvent(evento.getName(), evento.getDescription(), evento.getMaxPeople(), evento.getPlace(), notificaService);
        Evento savedEvento = eventoRepository.save(evento);
        animatore.getEventsCreated().add(savedEvento);
        animatoreRepository.save(animatore);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }

    /**
     * Recupera tutti gli eventi disponibili.
     *
     * @return ResponseEntity contenente la lista degli eventi
     */
    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi() {
        List<Evento> eventi = eventoRepository.findAll();
        return ResponseEntity.ok(eventi);
    }

    /**
     * Recupera un evento specifico per ID.
     *
     * @param id Identificativo dell'evento
     * @return ResponseEntity con l'evento trovato o un codice di errore se non esiste
     */
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Aggiorna un evento esistente.
     *
     * @param id Identificativo dell'evento da aggiornare
     * @param eventoData Dati aggiornati dell'evento
     * @return ResponseEntity con l'evento aggiornato o un codice di errore se non esiste
     */
    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento eventoData) {
        return eventoRepository.findById(id)
                .map(existing -> {
                    existing.setName(eventoData.getName());
                    existing.setDescription(eventoData.getDescription());
                    existing.setMaxPeople(eventoData.getMaxPeople());
                    existing.setPlace(eventoData.getPlace());
                    Evento updated = eventoRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina un evento specifico per ID.
     *
     * @param id Identificativo dell'evento da eliminare
     * @return ResponseEntity senza contenuto se l'operazione ha successo, o un codice di errore se non esiste
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
