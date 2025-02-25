package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Social.ContenutoSocial;
import com.example.filiera_francoletti_belardinelli_raiola.repository.SocialRepository;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerSocial;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerVenditore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/social")
public class ContenutoSocialController {


    private final HandlerSocial socialService;

    @Autowired
    public ContenutoSocialController(HandlerSocial socialService) {
        this.socialService = socialService;
    }
    // POST: Crea un nuovo contenuto social
    @PostMapping
    public ResponseEntity<?> addSocialContent(@RequestBody ContenutoSocial socialContent) {
        try {
            ContenutoSocial created = socialService.addSocialContent(socialContent);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // GET: Recupera tutti i contenuti social
    @GetMapping
    public ResponseEntity<List<ContenutoSocial>> getAllSocialContent() {
        List<ContenutoSocial> list = socialService.getAllSocialContent();
        return ResponseEntity.ok(list);
    }

    // GET: Recupera un contenuto social per ID
    @GetMapping("/{id}")
    public ResponseEntity<ContenutoSocial> getSocialContentById(@PathVariable Long id) {
        ContenutoSocial cs = socialService.getSocialContentById(id);
        if (cs == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cs);
    }

    // PUT: Aggiorna un contenuto social
    @PutMapping("/{id}")
    public ResponseEntity<ContenutoSocial> updateSocialContent(@PathVariable Long id, @RequestBody ContenutoSocial socialContentData) {
        try {
            ContenutoSocial updated = socialService.updateSocialContent(id, socialContentData);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Elimina un contenuto social
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocialContent(@PathVariable Long id) {
        socialService.deleteSocialContent(id);
        return ResponseEntity.noContent().build();
    }
}


