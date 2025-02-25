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

@RestController
@RequestMapping("/api/v1/animatori")
public class AnimatoreController {

    private final AnimatoreRepository animatoreRepository;
    private final EventoRepository eventoRepository;
    private final HandlerNotifica notificaService;
    private final HandlerAnimatore handlerAnimatore;
    private final UtenteRepository utenteRepository;



    @Autowired
    public AnimatoreController(UtenteRepository utenteRepository,HandlerAnimatore handlerAnimatore,AnimatoreRepository animatoreRepository, EventoRepository eventoRepository, HandlerNotifica notificaService) {
        this.animatoreRepository = animatoreRepository;
        this.eventoRepository = eventoRepository;
        this.notificaService = notificaService;
        this.handlerAnimatore = handlerAnimatore;
        this.utenteRepository = utenteRepository;
    }

    // POST: Crea un nuovo Animatore
    @PostMapping
    public ResponseEntity<AnimatoreDellaFiliera> createAnimatore(@RequestBody AnimatoreDellaFiliera animatore) {
        AnimatoreDellaFiliera saved = animatoreRepository.save(animatore);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    // Endpoint per iscrivere un utente alle notifiche di un animatore
    @PostMapping("/{animatoreId}/subscribe/{utenteId}")
    public ResponseEntity<String> subscribeToAnimatore(@PathVariable Long animatoreId,
                                                       @PathVariable Long utenteId) {
        try {
            handlerAnimatore.subscribe(animatoreId, utenteId);
            return ResponseEntity.ok("Utente con id " + utenteId + " iscritto alle notifiche dell'animatore " + animatoreId);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/{animatoreId}/unsubscribe/{utenteId}")
    public ResponseEntity<String> unsubscribeFromAnimatore(@PathVariable Long animatoreId, @PathVariable Long utenteId) {
        // Non usare UtenteRepository.findById, bensì usa l'istanza iniettata
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        UtenteGenerico utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + utenteId));

        animatore.unsubscribe(utente);
        animatoreRepository.save(animatore);

        return ResponseEntity.ok("Utente disiscritto con successo dall'animatore " + animatoreId);
    }

    // GET: Recupera tutti gli Animatori
    @GetMapping
    public ResponseEntity<List<AnimatoreDellaFiliera>> getAllAnimatori() {
        List<AnimatoreDellaFiliera> animatori = animatoreRepository.findAll();
        return ResponseEntity.ok(animatori);
    }

    // GET: Recupera un Animatore per ID
    @GetMapping("/{id}")
    public ResponseEntity<AnimatoreDellaFiliera> getAnimatoreById(@PathVariable Long id) {
        return animatoreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint per creare un evento e notificare automaticamente i subscriber
    @PostMapping("/{animatoreId}/eventi")
    public ResponseEntity<Evento> createEvento(@PathVariable Long animatoreId, @RequestBody Evento evento) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        // Imposta il creatore e crea l'evento, notificando i subscriber
        evento.setCreator(animatore);
        Evento savedEvento = eventoRepository.save(evento);
        animatore.getEventsCreated().add(savedEvento);
        // Chiamata al metodo che notifica i subscriber
        animatore.createEvent(evento.getName(), evento.getDescription(), evento.getMaxPeople(), evento.getPlace(), notificaService);
        animatoreRepository.save(animatore);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }

    // GET: Recupera tutti gli Eventi creati da un Animatore
    @GetMapping("/{animatoreId}/eventi")
    public ResponseEntity<List<Evento>> getEventiByAnimatore(@PathVariable Long animatoreId) {
        // Utilizziamo il repository degli eventi per filtrare per creatorId
        List<Evento> eventi = eventoRepository.findByCreatorId(animatoreId);
        return ResponseEntity.ok(eventi);
    }

    // GET: Recupera un Evento specifico dell'Animatore
    @GetMapping("/{animatoreId}/eventi/{eventoId}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long animatoreId, @PathVariable Long eventoId) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(animatoreId)
                .orElseThrow(() -> new RuntimeException("Animatore non trovato con id: " + animatoreId));
        return animatore.getEventsCreated().stream()
                .filter(evento -> evento.getId().equals(eventoId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Aggiorna un Animatore (ad esempio, il suo nome)
    @PutMapping("/{id}")
    public ResponseEntity<AnimatoreDellaFiliera> updateAnimatore(@PathVariable Long id, @RequestBody AnimatoreDellaFiliera animatoreData) {
        return animatoreRepository.findById(id)
                .map(existing -> {
                    existing.setName(animatoreData.getName());
                    AnimatoreDellaFiliera updated = animatoreRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Elimina un Animatore
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimatore(@PathVariable Long id) {
        if (animatoreRepository.existsById(id)) {
            animatoreRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
