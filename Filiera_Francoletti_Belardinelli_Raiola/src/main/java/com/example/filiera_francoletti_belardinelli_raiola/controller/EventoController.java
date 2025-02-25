package com.example.filiera_francoletti_belardinelli_raiola.controller;


import com.example.filiera_francoletti_belardinelli_raiola.model.Events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.UtenteGenerico;
import com.example.filiera_francoletti_belardinelli_raiola.repository.AnimatoreRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.EventoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerNotifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eventi")
public class EventoController {

    private final EventoRepository eventoRepository;
    private final AnimatoreRepository animatoreRepository;
    private final HandlerNotifica notificaService;

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
     */
    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        // 1. Verifica che l'evento abbia un 'creator' (animatore) associato
        AnimatoreDellaFiliera animatore = evento.getCreator();
        if (animatore == null || animatore.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        // 2. Recupera l'animatore dal DB per avere la lista aggiornata dei subscriber
        AnimatoreDellaFiliera animatoreFromDb = animatoreRepository.findById(animatore.getId())
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatore.getId()));

        // 3. Imposta l'animatore come creatore effettivo dell'evento
        evento.setCreator(animatoreFromDb);

        // 4. Salva l'evento
        Evento savedEvento = eventoRepository.save(evento);

        // 5. Aggiunge l'evento alla lista dell'animatore e salva l'animatore
        animatoreFromDb.getEventsCreated().add(savedEvento);
        animatoreRepository.save(animatoreFromDb);

        // 6. Notifica i subscriber
        notifySubscribers(animatoreFromDb, savedEvento);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }

    /**
     * Notifica i subscriber dell'animatore riguardo l'evento appena creato.
     */
    private void notifySubscribers(AnimatoreDellaFiliera animatore, Evento event) {
        // Per ogni subscriber, crea una notifica persistente
        animatore.getSubscribers().forEach(subscriber -> {
            Long subscriberId = ((UtenteGenerico) subscriber).getId();  // supponendo che Subscriber sia UtenteGenerico
            String message = "Nuovo evento: " + event.getName()
                    + " creato dall'animatore " + animatore.getName();
            notificaService.creaNotifica(message, subscriberId);

            // Facoltativo: notifica in tempo reale (pattern Observer in memoria)
            subscriber.update();
        });
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi() {
        List<Evento> eventi = eventoRepository.findAll();
        return ResponseEntity.ok(eventi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento eventoData) {
        return eventoRepository.findById(id)
                .map(existing -> {
                    existing.setName(eventoData.getName());
                    existing.setDescription(eventoData.getDescription());
                    existing.setMaxPeople(eventoData.getMaxPeople());
                    existing.setPlace(eventoData.getPlace());
                    // Il campo creator in genere non va modificato
                    Evento updated = eventoRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}