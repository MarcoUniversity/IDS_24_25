package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.repository.AnimatoreRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.EventoRepository;
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

    @Autowired
    public AnimatoreController(AnimatoreRepository animatoreRepository, EventoRepository eventoRepository) {
        this.animatoreRepository = animatoreRepository;
        this.eventoRepository = eventoRepository;
    }

    @PostMapping
    public ResponseEntity<AnimatoreDellaFiliera> createAnimatore(@RequestBody AnimatoreDellaFiliera animatore) {
        AnimatoreDellaFiliera saved = animatoreRepository.save(animatore);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<AnimatoreDellaFiliera>> getAllAnimatori() {
        List<AnimatoreDellaFiliera> animatori = animatoreRepository.findAll();
        return ResponseEntity.ok(animatori);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimatoreDellaFiliera> getAnimatoreById(@PathVariable Long id) {
        return animatoreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Aggiunge un evento creato dall'animatore
    @PostMapping("/{id}/eventi")
    public ResponseEntity<Evento> createEvento(@PathVariable Long id, @RequestBody Evento evento) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(id).orElse(null);
        if (animatore == null) {
            return ResponseEntity.notFound().build();
        }
        // Imposta il creatore dell'evento
        evento.setCreator(animatore);
        Evento savedEvento = eventoRepository.save(evento);
        // Aggiunge l'evento all'animatore
        animatore.getEventsCreated().add(savedEvento);
        animatoreRepository.save(animatore);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }

    @GetMapping("/{id}/eventi")
    public ResponseEntity<List<Evento>> getEventiByAnimatore(@PathVariable Long id) {
        List<Evento> eventi = eventoRepository.findByCreatorId(id);
        return ResponseEntity.ok(eventi);
    }



    /*@GetMapping("/{id}/eventi")
    public ResponseEntity<List<Evento>> getEventiByAnimatore(@PathVariable Long id) {
        AnimatoreDellaFiliera animatore = animatoreRepository.findById(id).orElse(null);
        if (animatore == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animatore.getEventsCreated());
    }*/
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimatore(@PathVariable Long id) {
        if (animatoreRepository.existsById(id)) {
            animatoreRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
