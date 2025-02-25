package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.repository.InvitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione degli inviti agli eventi.
 * Fornisce operazioni CRUD per gli inviti.
 */
@RestController
@RequestMapping("/api/v1/inviti")
public class InvitoController {

    private final InvitoRepository invitoRepository;

    /**
     * Costruttore per iniettare il repository degli inviti.
     *
     * @param invitoRepository il repository degli inviti
     */
    @Autowired
    public InvitoController(InvitoRepository invitoRepository) {
        this.invitoRepository = invitoRepository;
    }

    /**
     * Crea un nuovo invito.
     *
     * @param invito l'invito da creare
     * @return ResponseEntity contenente l'invito creato con stato HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Invito> createInvito(@RequestBody Invito invito) {
        Invito saved = invitoRepository.save(invito);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Recupera un invito dato il suo ID.
     *
     * @param id l'ID dell'invito
     * @return ResponseEntity contenente l'invito se trovato, altrimenti HTTP 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Invito> getInvitoById(@PathVariable Long id) {
        return invitoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Recupera tutti gli inviti esistenti.
     *
     * @return ResponseEntity contenente la lista degli inviti con stato HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Invito>> getAllInviti() {
        List<Invito> inviti = invitoRepository.findAll();
        return ResponseEntity.ok(inviti);
    }

    /**
     * Aggiorna un invito esistente.
     *
     * @param id         l'ID dell'invito da aggiornare
     * @param invitoData i nuovi dati dell'invito
     * @return ResponseEntity contenente l'invito aggiornato se esiste, altrimenti HTTP 404 (Not Found)
     */
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

    /**
     * Elimina un invito dato il suo ID.
     *
     * @param id l'ID dell'invito da eliminare
     * @return ResponseEntity con stato HTTP 204 (No Content) se eliminato con successo
     */
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
