package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerVenditore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prodotti")
public class ProdottoController {

    private final HandlerVenditore venditoreService;

    @Autowired
    public ProdottoController(HandlerVenditore venditoreService) {
        this.venditoreService = venditoreService;
    }

    // Endpoint per creare un prodotto per un venditore
    // Per semplicità, supponiamo di avere il venditore già ottenuto (ad esempio, tramite venditoreRepository)
    @PostMapping("/create")
    public ResponseEntity<Prodotto> createProduct(@RequestParam Long venditoreId,
                                                  @RequestParam String name,
                                                  @RequestParam double price,
                                                  @RequestParam String description,
                                                  @RequestParam long expirationMillis) {
        // Recupera il venditore da un ipotetico repository (non mostrato qui) o usa un dummy
        Venditore venditore = /* recupera il venditore dal repository in base all'ID */ null;
        if (venditore == null) {
            return ResponseEntity.badRequest().build();
        }
        Date expiration = new Date(expirationMillis);
        Prodotto prodotto = venditoreService.createProductForVenditore(venditore, name, price, description, expiration);
        return ResponseEntity.status(201).body(prodotto);
    }

    // Endpoint per aggiornare un prodotto
    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> updateProduct(@PathVariable Long id, @RequestBody Prodotto prodottoData) {
        Prodotto updated = venditoreService.updateProduct(id, prodottoData);
        return ResponseEntity.ok(updated);
    }

    // Endpoint per eliminare un prodotto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        venditoreService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint per ottenere un prodotto per ID
    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProductById(@PathVariable Long id) {
        Prodotto prodotto = venditoreService.getProductById(id);
        if (prodotto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prodotto);
    }

    // Endpoint per ottenere tutti i prodotti
    @GetMapping
    public ResponseEntity<List<Prodotto>> getAllProducts() {
        List<Prodotto> prodotti = venditoreService.getAllProducts();
        return ResponseEntity.ok(prodotti);
    }
}
