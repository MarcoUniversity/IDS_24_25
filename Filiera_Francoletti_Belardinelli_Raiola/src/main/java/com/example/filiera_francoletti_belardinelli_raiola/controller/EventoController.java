package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.model.events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.repository.AnimatoreRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.EventoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerNotifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per la gestione degli eventi.
 * <p>
 * Fornisce endpoint per creare, recuperare, aggiornare ed eliminare eventi.
 * In particolare, consente di creare un evento associato ad un animatore e notifica automaticamente i subscriber.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/eventi")
public class EventoController {

    private final EventoRepository eventoRepository;
    private final AnimatoreRepository animatoreRepository;
    private final HandlerNotifica notificaService;

    /**
     * Costruttore per l'iniezione delle dipendenze necessarie.
     *
     * @param eventoRepository     repository per la gestione degli eventi
     * @param animatoreRepository  repository per la gestione degli animatori
     * @param notificaService      servizio per la gestione delle notifiche
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
     * Crea un nuovo evento e, tramite il metodo dell'animatore, notifica automaticamente i subscriber.
     * <p>
     * Il JSON di input deve contenere almeno i campi "name", "description", "maxPeople", "place"
     * e un oggetto "creator" con l'id dell'animatore.
     * </p>
     *
     * @param evento l'oggetto {@link Evento} da creare
     * @return una {@link ResponseEntity} contenente l'evento creato con stato HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        // Verifica che l'evento contenga un creatore (animatore) valido
        if (evento.getCreator() == null || evento.getCreator().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        AnimatoreDellaFiliera animatore = this.animatoreRepository.findById(evento.getCreator().getId())
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + evento.getCreator().getId()));
        evento.setCreator(animatore);
        animatore.createEvent(evento.getName(), evento.getDescription(), evento.getMaxPeople(), evento.getPlace(), this.notificaService);
        this.animatoreRepository.save(animatore);
        List<Evento> eventi = animatore.getEventsCreated();
        Evento savedEvento = eventi.get(eventi.size() - 1);
        this.eventoRepository.save(savedEvento);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }

    /**
     * Recupera la lista di tutti gli eventi.
     *
     * @return una {@link ResponseEntity} contenente la lista di tutti gli eventi
     */
    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi() {
        List<Evento> eventi = eventoRepository.findAll();
        return ResponseEntity.ok(eventi);
    }

    /**
     * Recupera un evento in base al suo ID.
     *
     * @param id l'ID dell'evento da recuperare
     * @return una {@link ResponseEntity} contenente l'evento trovato oppure lo stato HTTP 404 (Not Found) se non esiste
     */
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Aggiorna i dati di un evento esistente.
     * <p>
     * Solo i campi name, description, maxPeople e place vengono aggiornati;
     * il campo creator non viene modificato.
     * </p>
     *
     * @param id          l'ID dell'evento da aggiornare
     * @param eventoData  i nuovi dati dell'evento
     * @return una {@link ResponseEntity} contenente l'evento aggiornato oppure lo stato HTTP 404 se non trovato
     */
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

    /**
     * Elimina un evento in base al suo ID.
     *
     * @param id l'ID dell'evento da eliminare
     * @return una {@link ResponseEntity} senza contenuto se eliminato con successo,
     *         oppure lo stato HTTP 404  se l'evento non esiste
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        return eventoRepository.findById(id)
                .<ResponseEntity<Void>>map(evento -> {
                    // 1. Dissocia l'evento dall'animatore, se necessario
                    AnimatoreDellaFiliera animatore = evento.getCreator();
                    if (animatore != null) {
                        animatore.getEventsCreated().remove(evento);
                        animatoreRepository.save(animatore);
                    }
                    // 2. Elimina l'evento
                    eventoRepository.delete(evento);
                    // 3. Restituisce 204 No Content
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
