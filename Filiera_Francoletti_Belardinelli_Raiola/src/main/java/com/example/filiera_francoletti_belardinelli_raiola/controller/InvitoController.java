package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.repository.InvitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inviti")
public class InvitoController {

    private final InvitoRepository invitoRepository;

    @Autowired
    public InvitoController(InvitoRepository invitoRepository) {
        this.invitoRepository = invitoRepository;
    }

    // Crea un nuovo invito
    @PostMapping
    public ResponseEntity<Invito> createInvito(@RequestBody Invito invito) {
        // Assicurati che l'oggetto invito contenga almeno sender, receiver ed event con i loro ID (e se necessario il campo "tipo" per il polimorfismo)
        Invito saved = invitoRepository.save(invito);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Recupera un invito per ID
    @GetMapping("/{id}")
    public ResponseEntity<Invito> getInvitoById(@PathVariable Long id) {
        return invitoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Recupera tutti gli inviti
    @GetMapping
    public ResponseEntity<List<Invito>> getAllInviti() {
        List<Invito> inviti = invitoRepository.findAll();
        return ResponseEntity.ok(inviti);
    }

    // Aggiorna un invito
    @PutMapping("/{id}")
    public ResponseEntity<Invito> updateInvito(@PathVariable Long id, @RequestBody Invito invitoData) {
        return invitoRepository.findById(id)
                .map(existing -> {
                    existing.setSender(invitoData.getSender());
                    existing.setReceiver(invitoData.getReceiver());
                    existing.setEvent(invitoData.getEvent());
                    existing.setDescription(invitoData.getDescription());
                    existing.setAccepted(invitoData.isAccepted());
                    Invito updated = invitoRepository.save(existing);
                    return ResponseEntity.ok(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Elimina un invito per ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvito(@PathVariable Long id) {
        invitoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Nuovo endpoint: Accetta un invito (imposta accepted a true)
    @PutMapping("/{id}/accept")
    public ResponseEntity<Invito> acceptInvito(@PathVariable Long id) {
        return invitoRepository.findById(id)
                .map(invito -> {
                    invito.setAccepted(true);
                    Invito updated = invitoRepository.save(invito);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

