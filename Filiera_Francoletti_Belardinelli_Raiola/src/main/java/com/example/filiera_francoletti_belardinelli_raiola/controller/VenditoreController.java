package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione dei venditori.
 * Fornisce endpoint per creare, recuperare, aggiornare ed eliminare venditori.
 */
@RestController
@RequestMapping("/api/v1/venditori")
public class VenditoreController {

    private final VenditoreRepository venditoreRepository;

    /**
     * Costruttore del controller per la gestione dei venditori.
     *
     * @param venditoreRepository Il repository per l'accesso ai dati dei venditori.
     */
    @Autowired
    public VenditoreController(VenditoreRepository venditoreRepository) {
        this.venditoreRepository = venditoreRepository;
    }

    /**
     * Crea un nuovo venditore.
     *
     * @param venditore L'oggetto Venditore da creare.
     * @return Una ResponseEntity contenente il venditore creato e lo status HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Venditore> createVenditore(@RequestBody Venditore venditore) {
        Venditore saved = venditoreRepository.save(venditore);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Recupera tutti i venditori.
     *
     * @return Una ResponseEntity contenente la lista di tutti i venditori.
     */
    @GetMapping
    public ResponseEntity<List<Venditore>> getAllVenditori() {
        List<Venditore> venditori = venditoreRepository.findAll();
        return ResponseEntity.ok(venditori);
    }

    /**
     * Recupera un venditore in base al suo id.
     *
     * @param id L'ID del venditore da recuperare.
     * @return Una ResponseEntity contenente il venditore se trovato, altrimenti lo status HTTP 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Venditore> getVenditoreById(@PathVariable Long id) {
        return venditoreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Aggiorna i dati di un venditore esistente.
     *
     * @param id L'id del venditore da aggiornare.
     * @param venditoreData I dati aggiornati del venditore.
     * @return Una ResponseEntity contenente il venditore aggiornato, oppure lo status HTTP 404 se il venditore non esiste.
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
     * Elimina un venditore in base al suo id.
     *
     * @param id L'ID del venditore da eliminare.
     * @return Una ResponseEntity senza contenuto (HTTP 204 No Content) se l'eliminazione avviene con successo,
     *         altrimenti lo status HTTP 404 (Not Found) se il venditore non esiste.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenditore(@PathVariable Long id) {
        if (venditoreRepository.existsById(id)) {
            venditoreRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
