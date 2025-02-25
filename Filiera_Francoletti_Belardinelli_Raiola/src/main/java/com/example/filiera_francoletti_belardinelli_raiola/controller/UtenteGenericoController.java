package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.users.UtenteGenerico;
import com.example.filiera_francoletti_belardinelli_raiola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller per la gestione degli utenti generici.
 * Fornisce endpoint per la creazione, il recupero, l'aggiornamento e la cancellazione degli utenti.
 */
@RestController
@RequestMapping("/api/v1/utenti")
public class UtenteGenericoController {

    private final UtenteRepository utenteRepository;

    /**
     * Costruttore del controller per la gestione degli utenti.
     *
     * @param utenteRepository Il repository per l'accesso ai dati degli utenti.
     */
    @Autowired
    public UtenteGenericoController(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    /**
     * Crea un nuovo utente.
     *
     * @param utente L'oggetto UtenteGenerico da creare.
     * @return Una ResponseEntity contenente l'utente creato e lo status HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<UtenteGenerico> createUtente(@RequestBody UtenteGenerico utente) {
        UtenteGenerico saved = this.utenteRepository.save(utente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Recupera un utente in base al suo ID.
     *
     * @param id L'ID dell'utente da recuperare.
     * @return Una ResponseEntity contenente l'utente se trovato, altrimenti lo status HTTP 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<UtenteGenerico> getUtente(@PathVariable Long id) {
        Optional<UtenteGenerico> utente = this.utenteRepository.findById(id);
        return utente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Aggiorna i dati di un utente esistente.
     *
     * @param id L'ID dell'utente da aggiornare.
     * @param utenteData I dati aggiornati dell'utente.
     * @return Una ResponseEntity contenente l'utente aggiornato, oppure lo status HTTP 404 se l'utente non esiste.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UtenteGenerico> updateUtente(@PathVariable Long id, @RequestBody UtenteGenerico utenteData) {
        Optional<UtenteGenerico> opt = this.utenteRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UtenteGenerico u = opt.get();
        u.setName(utenteData.getName());
        u.setEmail(utenteData.getEmail());
        u.setPassword(utenteData.getPassword());
        UtenteGenerico updated = this.utenteRepository.save(u);
        return ResponseEntity.ok(updated);
    }

    /**
     * Elimina un utente in base al suo ID.
     *
     * @param id L'ID dell'utente da eliminare.
     * @return Una ResponseEntity senza contenuto (HTTP 204 No Content) se l'eliminazione avviene con successo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable Long id) {
        this.utenteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Recupera la lista di tutti gli utenti.
     *
     * @return Una ResponseEntity contenente la lista di tutti gli utenti presenti nel sistema.
     */
    @GetMapping
    public ResponseEntity<List<UtenteGenerico>> getAllUtenti() {
        List<UtenteGenerico> utenti = this.utenteRepository.findAll();
        return ResponseEntity.ok(utenti);
    }
}
