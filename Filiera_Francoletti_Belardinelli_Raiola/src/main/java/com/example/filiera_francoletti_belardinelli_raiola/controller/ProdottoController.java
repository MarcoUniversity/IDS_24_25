package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerVenditore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/prodotti")
public class ProdottoController {

    private final HandlerVenditore venditoreService;
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public ProdottoController(HandlerVenditore venditoreService, ProdottoRepository prodottoRepository) {
        this.venditoreService = venditoreService;
        this.prodottoRepository = prodottoRepository;
    }

    @PostMapping
    public ResponseEntity<Prodotto> createProduct(@RequestBody Prodotto prodotto) {
        // Verifica che seller e processingLocation siano valorizzati
        if (prodotto.getSeller() == null || prodotto.getSeller().getId() == null || prodotto.getProcessingLocation() == null) {
            return ResponseEntity.badRequest().build();
        }
        Prodotto created = venditoreService.createProductForVenditore(
                prodotto.getSeller(),
                prodotto.getName(),
                prodotto.getPrice(),
                prodotto.getDescription(),
                prodotto.getExpiration(),
                prodotto.getProcessingLocation()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping
    public ResponseEntity<List<Prodotto>> getAllProducts() {
        // Restituisce solo i prodotti verificati
        List<Prodotto> prodotti = prodottoRepository.findByStateTrue();
        return ResponseEntity.ok(prodotti);
    }

    @GetMapping("/venditore/{sellerId}")
    public ResponseEntity<List<Prodotto>> getProductsBySeller(@PathVariable Long sellerId) {
        List<Prodotto> prodotti = prodottoRepository.findBySellerIdAndStateTrue(sellerId);
        return ResponseEntity.ok(prodotti);
    }


    /* GET: restituisce solo i prodotti verificati (state == true)
    @GetMapping
    public ResponseEntity<List<Prodotto>> getAllProducts() {
        List<Prodotto> all = prodottoRepository.findAll();
        List<Prodotto> prodottiVerificati = all.stream()
                .filter(Prodotto::isState)
                .collect(Collectors.toList());
        return ResponseEntity.ok(prodottiVerificati);
    }*/

    // GET: restituisce il prodotto se esiste e se è verificato, altrimenti 404
    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProductById(@PathVariable Long id) {
        return prodottoRepository.findById(id)
                .filter(Prodotto::isState)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> updateProduct(@PathVariable Long id, @RequestBody Prodotto prodottoData) {
        Prodotto updated = venditoreService.updateProduct(id, prodottoData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdotto(@PathVariable Long id) {
        if (prodottoRepository.existsById(id)) {
            prodottoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /* GET: Recupera i prodotti di un venditore (solo quelli verificati)
    @GetMapping("/venditore/{sellerId}")
    public ResponseEntity<List<Prodotto>> getProductsBySeller(@PathVariable Long sellerId) {
        List<Prodotto> prodotti = prodottoRepository.findAll().stream()
                .filter(p -> p.getSeller() != null
                        && p.getSeller().getId().equals(sellerId)
                        && p.isState())
                .collect(Collectors.toList());
        return ResponseEntity.ok(prodotti);
    }*/
}

