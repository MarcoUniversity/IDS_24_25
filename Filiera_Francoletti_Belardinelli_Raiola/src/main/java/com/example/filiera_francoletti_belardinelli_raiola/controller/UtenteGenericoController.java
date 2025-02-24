package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Users.UtenteGenerico;
import com.example.filiera_francoletti_belardinelli_raiola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/utenti")
public class UtenteGenericoController {

    private final UtenteRepository utenteRepository;

    @Autowired
    public UtenteGenericoController(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @PostMapping
    public ResponseEntity<UtenteGenerico> createUtente(@RequestBody UtenteGenerico utente) {
        UtenteGenerico saved = utenteRepository.save(utente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteGenerico> getUtente(@PathVariable Long id) {
        Optional<UtenteGenerico> utente = utenteRepository.findById(id);
        return utente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtenteGenerico> updateUtente(@PathVariable Long id, @RequestBody UtenteGenerico utenteData) {
        Optional<UtenteGenerico> opt = utenteRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UtenteGenerico u = opt.get();
        u.setName(utenteData.getName());
        u.setEmail(utenteData.getEmail());
        u.setPassword(utenteData.getPassword());
        UtenteGenerico updated = utenteRepository.save(u);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable Long id) {
        utenteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UtenteGenerico>> getAllUtenti() {
        List<UtenteGenerico> utenti = utenteRepository.findAll();
        return ResponseEntity.ok(utenti);
    }
}

