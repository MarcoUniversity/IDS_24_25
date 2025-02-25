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
     * Crea un nuovo evento e, tramite il metodo dell'animatore, notifica automaticamente i subscriber.
     * Il JSON di input deve contenere almeno i campi "name", "description", "maxPeople", "place"
     * e un oggetto "creator" con l'id dell'animatore.
     */
    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        // Verifica che l'evento contenga un creatore (animatore) valido
        if (evento.getCreator() == null || evento.getCreator().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        // Recupera l'animatore dal database
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(evento.getCreator().getId())
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + evento.getCreator().getId()));

        // Imposta l'animatore come creatore effettivo dell'evento
        evento.setCreator(animatore);

        // Usa il metodo createEvent dell'animatore che crea l'evento e notifica i subscriber
        animatore.createEvent(evento.getName(), evento.getDescription(), evento.getMaxPeople(), evento.getPlace(), notificaService);

        // Salva l'animatore per persistire la modifica (la lista degli eventi)
        animatoreRepository.save(animatore);

        // Ottieni l'ultimo evento creato dall'animatore (si assume venga aggiunto alla fine della lista)
        List<Evento> eventi = animatore.getEventsCreated();
        Evento savedEvento = eventi.get(eventi.size() - 1);

        // Puoi salvare anche l'evento nel repository, se necessario
        eventoRepository.save(savedEvento);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }

    // Altri endpoint GET, PUT, DELETE per gli eventi…

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
                    // Il campo creator non viene modificato
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
