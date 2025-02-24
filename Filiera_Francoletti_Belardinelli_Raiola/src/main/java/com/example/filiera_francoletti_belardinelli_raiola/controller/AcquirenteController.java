package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Acquirente;
import com.example.filiera_francoletti_belardinelli_raiola.repository.AcquirenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/acquirenti")
public class AcquirenteController {

    private final AcquirenteRepository acquirenteRepository;

    @Autowired
    public AcquirenteController(AcquirenteRepository acquirenteRepository) {
        this.acquirenteRepository = acquirenteRepository;
    }

    @PostMapping
    public ResponseEntity<Acquirente> createAcquirente(@RequestBody Acquirente acquirente) {
        Acquirente saved = acquirenteRepository.save(acquirente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acquirente> getAcquirente(@PathVariable Long id) {
        Optional<Acquirente> acquirente = acquirenteRepository.findById(id);
        return acquirente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Acquirente> updateAcquirente(@PathVariable Long id, @RequestBody Acquirente acquirenteData) {
        return acquirenteRepository.findById(id)
                .map(existing -> {
                    existing.setName(acquirenteData.getName());
                    existing.setEmail(acquirenteData.getEmail());
                    existing.setPassword(acquirenteData.getPassword());
                    Acquirente updated = acquirenteRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcquirente(@PathVariable Long id) {
        if (acquirenteRepository.existsById(id)) {
            acquirenteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Acquirente>> getAllAcquirenti() {
        List<Acquirente> acquirenti = acquirenteRepository.findAll();
        return ResponseEntity.ok(acquirenti);
    }
}
