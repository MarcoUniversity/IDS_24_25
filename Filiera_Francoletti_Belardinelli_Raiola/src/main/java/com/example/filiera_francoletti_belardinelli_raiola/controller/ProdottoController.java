package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerVenditore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prodotti")
public class ProdottoController {

    private final HandlerVenditore venditoreService;

    @Autowired
    public ProdottoController(HandlerVenditore venditoreService) {
        this.venditoreService = venditoreService;
    }

    @PostMapping
    public ResponseEntity<Prodotto> createProduct(@RequestBody Prodotto prodotto) {
        // Verifica che l'oggetto "seller" sia valorizzato correttamente
        Venditore venditore = prodotto.getSeller();
        if (venditore == null || venditore.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Prodotto created = venditoreService.createProductForVenditore(
                venditore,
                prodotto.getName(),
                prodotto.getPrice(),
                prodotto.getDescription(),
                prodotto.getExpiration()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> updateProduct(@PathVariable Long id, @RequestBody Prodotto prodottoData) {
        Prodotto updated = venditoreService.updateProduct(id, prodottoData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        venditoreService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProductById(@PathVariable Long id) {
        Prodotto prodotto = venditoreService.getProductById(id);
        if (prodotto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prodotto);
    }

    @GetMapping
    public ResponseEntity<List<Prodotto>> getAllProducts() {
        List<Prodotto> prodotti = venditoreService.getAllProducts();
        return ResponseEntity.ok(prodotti);
    }
}
