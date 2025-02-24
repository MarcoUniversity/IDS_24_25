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
        Optional<Acquirente> opt = acquirenteRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Acquirente a = opt.get();
        a.setName(acquirenteData.getName());
        a.setEmail(acquirenteData.getEmail());
        a.setPassword(acquirenteData.getPassword());
        Acquirente updated = acquirenteRepository.save(a);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcquirente(@PathVariable Long id) {
        acquirenteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Acquirente>> getAllAcquirenti() {
        List<Acquirente> acquirenti = acquirenteRepository.findAll();
        return ResponseEntity.ok(acquirenti);
    }
}
