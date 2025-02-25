package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per la gestione dei venditori.
 * <p>
 * Fornisce operazioni CRUD per i venditori, consentendo la creazione,
 * il recupero, l'aggiornamento e la cancellazione dei venditori.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/venditori")
public class VenditoreController {

    private final VenditoreRepository venditoreRepository;

    /**
     * Costruttore per iniettare il repository dei venditori.
     *
     * @param venditoreRepository il repository per la gestione dei venditori
     */
    @Autowired
    public VenditoreController(VenditoreRepository venditoreRepository) {
        this.venditoreRepository = venditoreRepository;
    }

    /**
     * Crea un nuovo venditore.
     *
     * @param venditore l'oggetto {@link Venditore} da creare
     * @return una {@link ResponseEntity} contenente il venditore creato e lo stato HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Venditore> createVenditore(@RequestBody Venditore venditore) {
        Venditore saved = venditoreRepository.save(venditore);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Recupera tutti i venditori esistenti.
     *
     * @return una {@link ResponseEntity} contenente la lista dei venditori
     *         e stato HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Venditore>> getAllVenditori() {
        List<Venditore> venditori = venditoreRepository.findAll();
        return ResponseEntity.ok(venditori);
    }

    /**
     * Recupera un venditore dato il suo ID.
     *
     * @param id l'ID del venditore da recuperare
     * @return una {@link ResponseEntity} contenente il venditore se trovato,
     *         altrimenti stato HTTP 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Venditore> getVenditoreById(@PathVariable Long id) {
        return venditoreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Aggiorna un venditore esistente.
     * <p>
     * I campi aggiornabili includono il nome e l'indirizzo del venditore.
     * </p>
     *
     * @param id            l'ID del venditore da aggiornare
     * @param venditoreData i nuovi dati da impostare sul venditore esistente
     * @return una {@link ResponseEntity} contenente il venditore aggiornato,
     *         oppure stato HTTP 404 se il venditore non esiste
     */
    @PutMapping("/{id}")
    public ResponseEntity<Venditore> updateVenditore(@PathVariable Long id, @RequestBody Venditore venditoreData) {
        return venditoreRepository.findById(id)
                .map(existing -> {
                    existing.setName(venditoreData.getName());
                    existing.setAddress(venditoreData.getAddress());
                    // Se ci sono campi specifici della sottoclasse, occorre gestirli in modo appropriato
                    Venditore updated = venditoreRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina un venditore dato il suo ID.
     *
     * @param id l'ID del venditore da eliminare
     * @return una {@link ResponseEntity} con stato HTTP 204 se l'eliminazione ha successo,
     *         altrimenti stato HTTP 404
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenditore(@PathVariable Long id) {
        if (venditoreRepository.existsById(id)) {
            venditoreRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
