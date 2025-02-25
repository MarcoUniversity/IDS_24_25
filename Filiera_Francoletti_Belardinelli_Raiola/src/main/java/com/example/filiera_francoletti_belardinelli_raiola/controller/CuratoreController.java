package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerCuratore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione dei curatori e dei prodotti in attesa di verifica.
 */
@RestController
@RequestMapping("/api/v1/curatori")
public class CuratoreController {

    private final HandlerCuratore curatoreService;

    /**
     * Costruttore per l'iniezione delle dipendenze.
     *
     * @param curatoreService Servizio per la gestione dei curatori.
     */
    @Autowired
    public CuratoreController(HandlerCuratore curatoreService) {
        this.curatoreService = curatoreService;
    }

    /**
     * Recupera la lista dei prodotti in attesa di verifica.
     *
     * @return ResponseEntity contenente la lista dei prodotti pendenti.
     */
    @GetMapping("/prodotti/pending")
    public ResponseEntity<List<Prodotto>> getPendingProducts() {
        List<Prodotto> pending = this.curatoreService.getPendingProducts();
        return ResponseEntity.ok(pending);
    }

    /**
     * Verifica un prodotto dato l'ID del curatore e l'ID del prodotto.
     *
     * @param curatoreId ID del curatore che effettua la verifica.
     * @param productId  ID del prodotto da verificare.
     * @return ResponseEntity contenente il prodotto verificato.
     */
    @PostMapping("/{curatoreId}/verify/{productId}")
    public ResponseEntity<Prodotto> verifyProduct(@PathVariable Long curatoreId,
                                                  @PathVariable Long productId) {
        Prodotto verified = this.curatoreService.verifyProduct(productId);
        return ResponseEntity.ok(verified);
    }

    /**
     * Recupera tutti i prodotti verificati di un determinato venditore.
     *
     * @param vendorId ID del venditore.
     * @return ResponseEntity contenente la lista dei prodotti verificati.
     */
    @GetMapping("/prodotti/vendor/{vendorId}")
    public ResponseEntity<List<Prodotto>> getVerifiedProductsByVendor(@PathVariable Long vendorId) {
        List<Prodotto> prodotti = this.curatoreService.getVerifiedProductsByVendor(vendorId);
        return ResponseEntity.ok(prodotti);
    }
}
