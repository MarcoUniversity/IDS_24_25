package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.repository.InvitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per la gestione degli inviti agli eventi.
 * <p>
 * Fornisce operazioni CRUD per gli inviti, permettendo la creazione,
 * il recupero, l'aggiornamento e la cancellazione degli inviti,
 * oltre a un endpoint specifico per accettare un invito.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/inviti")
public class InvitoController {

    private final InvitoRepository invitoRepository;

    /**
     * Costruttore per iniettare il repository degli inviti.
     *
     * @param invitoRepository il repository per la gestione degli inviti
     */
    @Autowired
    public InvitoController(InvitoRepository invitoRepository) {
        this.invitoRepository = invitoRepository;
    }

    /**
     * Crea un nuovo invito.
     *
     * @param invito l'oggetto {@link Invito} da creare
     * @return una {@link ResponseEntity} contenente l'invito creato e lo stato HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Invito> createInvito(@RequestBody Invito invito) {
        Invito saved = invitoRepository.save(invito);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Recupera un invito dato il suo ID.
     *
     * @param id l'ID dell'invito da recuperare
     * @return una {@link ResponseEntity} contenente l'invito se trovato,
     *         altrimenti stato HTTP 404 (Not Found)
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
     * @return una {@link ResponseEntity} contenente la lista degli inviti
     *         e stato HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Invito>> getAllInviti() {
        List<Invito> inviti = invitoRepository.findAll();
        return ResponseEntity.ok(inviti);
    }

    /**
     * Aggiorna un invito esistente.
     * <p>
     * I campi aggiornabili includono il mittente, il destinatario, l'evento,
     * la descrizione e lo stato di accettazione.
     * </p>
     *
     * @param id         l'ID dell'invito da aggiornare
     * @param invitoData i nuovi dati da impostare sull'invito esistente
     * @return una {@link ResponseEntity} contenente l'invito aggiornato,
     *         oppure stato HTTP 404 (Not Found) se l'invito non esiste
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
     * @return una {@link ResponseEntity} con stato HTTP 204 (No Content) se l'eliminazione ha successo,
     *         altrimenti stato HTTP 404 (Not Found)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvito(@PathVariable Long id) {
        if (invitoRepository.existsById(id)) {
            invitoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Accetta un invito impostando il campo accepted a true.
     *
     * @param id l'ID dell'invito da accettare
     * @return una {@link ResponseEntity} contenente l'invito aggiornato
     *         oppure stato HTTP 404 (Not Found) se l'invito non esiste
     */
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
