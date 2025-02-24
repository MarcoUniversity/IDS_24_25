package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venditori")
public class VenditoreController {

    private final VenditoreRepository venditoreRepository;

    @Autowired
    public VenditoreController(VenditoreRepository venditoreRepository) {
        this.venditoreRepository = venditoreRepository;
    }

    // Crea un venditore (polimorfico)
    @PostMapping
    public ResponseEntity<Venditore> createVenditore(@RequestBody Venditore venditore) {
        Venditore saved = venditoreRepository.save(venditore);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Recupera tutti i venditori
    @GetMapping
    public ResponseEntity<List<Venditore>> getAllVenditori() {
        List<Venditore> venditori = venditoreRepository.findAll();
        return ResponseEntity.ok(venditori);
    }

    // Recupera un venditore per ID
    @GetMapping("/{id}")
    public ResponseEntity<Venditore> getVenditoreById(@PathVariable Long id) {
        return venditoreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
