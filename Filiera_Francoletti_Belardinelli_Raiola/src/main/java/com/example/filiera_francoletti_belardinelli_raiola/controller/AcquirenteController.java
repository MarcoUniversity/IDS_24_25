package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Acquirente;
import com.example.filiera_francoletti_belardinelli_raiola.repository.AcquirenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller REST per gestire le operazioni CRUD sugli acquirenti.
 * Fornisce API per creare, leggere, aggiornare ed eliminare acquirenti.
 */
@RestController
@RequestMapping("/api/v1/acquirenti")
public class AcquirenteController {

    private final AcquirenteRepository acquirenteRepository;

    /**
     * Costruttore per l'iniezione della dipendenza AcquirenteRepository.
     *
     * @param acquirenteRepository il repository per gli acquirenti
     */
    @Autowired
    public AcquirenteController(AcquirenteRepository acquirenteRepository) {
        this.acquirenteRepository = acquirenteRepository;
    }

    /**
     * Crea un nuovo acquirente.
     *
     * @param acquirente l'oggetto Acquirente da salvare
     * @return ResponseEntity contenente l'acquirente salvato con stato HTTP 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Acquirente> createAcquirente(@RequestBody Acquirente acquirente) {
        Acquirente saved = this.acquirenteRepository.save(acquirente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Recupera un acquirente tramite ID.
     *
     * @param id l'ID dell'acquirente
     * @return ResponseEntity contenente l'acquirente se trovato, altrimenti 404 NOT FOUND
     */
    @GetMapping("/{id}")
    public ResponseEntity<Acquirente> getAcquirente(@PathVariable Long id) {
        Optional<Acquirente> acquirente = this.acquirenteRepository.findById(id);
        return acquirente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Aggiorna un acquirente esistente.
     *
     * @param id l'ID dell'acquirente da aggiornare
     * @param acquirenteData i nuovi dati dell'acquirente
     * @return ResponseEntity contenente l'acquirente aggiornato, oppure 404 NOT FOUND se non esiste
     */
    @PutMapping("/{id}")
    public ResponseEntity<Acquirente> updateAcquirente(@PathVariable Long id, @RequestBody Acquirente acquirenteData) {
        return this.acquirenteRepository.findById(id)
                .map(existing -> {
                    existing.setName(acquirenteData.getName());
                    existing.setEmail(acquirenteData.getEmail());
                    existing.setPassword(acquirenteData.getPassword());
                    Acquirente updated = this.acquirenteRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina un acquirente tramite ID.
     *
     * @param id l'ID dell'acquirente da eliminare
     * @return ResponseEntity con stato HTTP 204 NO CONTENT se eliminato, altrimenti 404 NOT FOUND
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcquirente(@PathVariable Long id) {
        if (this.acquirenteRepository.existsById(id)) {
            this.acquirenteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Recupera tutti gli acquirenti.
     *
     * @return ResponseEntity contenente la lista di tutti gli acquirenti
     */
    @GetMapping
    public ResponseEntity<List<Acquirente>> getAllAcquirenti() {
        List<Acquirente> acquirenti = acquirenteRepository.findAll();
        return ResponseEntity.ok(acquirenti);
    }
}
