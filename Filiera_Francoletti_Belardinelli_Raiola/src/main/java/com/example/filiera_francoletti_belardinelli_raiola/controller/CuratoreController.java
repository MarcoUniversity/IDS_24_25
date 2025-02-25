package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerCuratore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curatori")
public class CuratoreController {

    private final HandlerCuratore curatoreService;

    @Autowired
    public CuratoreController(HandlerCuratore curatoreService) {
        this.curatoreService = curatoreService;
    }

    @GetMapping("/prodotti/pending")
    public ResponseEntity<List<Prodotto>> getPendingProducts() {
        List<Prodotto> pending = this.curatoreService.getPendingProducts();
        return ResponseEntity.ok(pending);
    }

    // POST: Il curatore verifica un prodotto (data l'id del prodotto)
    @PostMapping("/{curatoreId}/verify/{productId}")
    public ResponseEntity<Prodotto> verifyProduct(@PathVariable Long curatoreId,
                                                  @PathVariable Long productId) {

        Prodotto verified = this.curatoreService.verifyProduct(productId);
        return ResponseEntity.ok(verified);
    }
    @GetMapping("/prodotti/vendor/{vendorId}")
    public ResponseEntity<List<Prodotto>> getVerifiedProductsByVendor(@PathVariable Long vendorId) {
        List<Prodotto> prodotti = this.curatoreService.getVerifiedProductsByVendor(vendorId);
        return ResponseEntity.ok(prodotti);
    }
}