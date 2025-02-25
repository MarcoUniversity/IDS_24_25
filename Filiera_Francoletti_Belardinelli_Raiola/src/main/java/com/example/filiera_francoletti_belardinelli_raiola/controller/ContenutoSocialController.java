package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Social.ContenutoSocial;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerSocial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per la gestione dei contenuti social.
 * Fornisce API per creare, recuperare, aggiornare ed eliminare contenuti social.
 */
@RestController
@RequestMapping("/api/v1/social")
public class ContenutoSocialController {

    private final HandlerSocial socialService;

    /**
     * Costruttore per l'iniezione delle dipendenze necessarie.
     *
     * @param socialService servizio per la gestione dei contenuti social
     */
    @Autowired
    public ContenutoSocialController(HandlerSocial socialService) {
        this.socialService = socialService;
    }

    /**
     * Crea un nuovo contenuto social.
     *
     * @param socialContent contenuto social da aggiungere
     * @return ResponseEntity contenente il contenuto creato o un messaggio di errore
     */
    @PostMapping
    public ResponseEntity<?> addSocialContent(@RequestBody ContenutoSocial socialContent) {
        try {
            ContenutoSocial created = socialService.addSocialContent(socialContent);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * Recupera tutti i contenuti social.
     *
     * @return ResponseEntity contenente la lista dei contenuti social
     */
    @GetMapping
    public ResponseEntity<List<ContenutoSocial>> getAllSocialContent() {
        List<ContenutoSocial> list = socialService.getAllSocialContent();
        return ResponseEntity.ok(list);
    }

    /**
     * Recupera un contenuto social tramite ID.
     *
     * @param id ID del contenuto social
     * @return ResponseEntity contenente il contenuto trovato o 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContenutoSocial> getSocialContentById(@PathVariable Long id) {
        ContenutoSocial cs = socialService.getSocialContentById(id);
        if (cs == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cs);
    }

    /**
     * Aggiorna un contenuto social esistente.
     *
     * @param id ID del contenuto da aggiornare
     * @param socialContentData nuovi dati del contenuto
     * @return ResponseEntity contenente il contenuto aggiornato o 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContenutoSocial> updateSocialContent(@PathVariable Long id, @RequestBody ContenutoSocial socialContentData) {
        try {
            ContenutoSocial updated = socialService.updateSocialContent(id, socialContentData);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un contenuto social.
     *
     * @param id ID del contenuto da eliminare
     * @return ResponseEntity con stato HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocialContent(@PathVariable Long id) {
        socialService.deleteSocialContent(id);
        return ResponseEntity.noContent().build();
    }
}
