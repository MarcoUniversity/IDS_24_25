package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerVenditore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione dei prodotti sulla piattaforma.
 * Fornisce degli endpoint per creare, leggere, aggiornare e cancellare prodotti.
 */
@RestController
@RequestMapping("/api/v1/prodotti")
public class ProdottoController {

    private final HandlerVenditore venditoreService;
    private final ProdottoRepository prodottoRepository;

    /**
     * Costruttore del controller per la gestione dei prodotti.
     *
     * @param venditoreService Il servizio per gestire i venditori e i prodotti.
     * @param prodottoRepository Il repository per l'accesso ai dati sui prodotti.
     */
    @Autowired
    public ProdottoController(HandlerVenditore venditoreService, ProdottoRepository prodottoRepository) {
        this.venditoreService = venditoreService;
        this.prodottoRepository = prodottoRepository;
    }

    /**
     * Crea un nuovo prodotto.
     * Verifica che il venditore e la location di lavorazione siano correttamente valorizzati.
     *
     * @param prodotto Il prodotto da creare.
     * @return Un {@link ResponseEntity} con il prodotto creato, o una risposta di errore se i dati non sono corretti.
     */
    @PostMapping
    public ResponseEntity<Prodotto> createProduct(@RequestBody Prodotto prodotto) {
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

    /**
     * Recupera tutti i prodotti verificati dalla piattaforma.
     *
     * @return Una lista di prodotti verificati.
     */
    @GetMapping
    public ResponseEntity<List<Prodotto>> getAllProducts() {
        // Restituisce solo i prodotti verificati
        List<Prodotto> prodotti = prodottoRepository.findByStateTrue();
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Recupera i prodotti di un venditore specifico.
     *
     * @param sellerId L'ID del venditore di cui recuperare i prodotti.
     * @return Una lista di prodotti verificati per quel venditore.
     */
    @GetMapping("/venditore/{sellerId}")
    public ResponseEntity<List<Prodotto>> getProductsBySeller(@PathVariable Long sellerId) {
        List<Prodotto> prodotti = prodottoRepository.findBySellerIdAndStateTrue(sellerId);
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Recupera un prodotto specifico, se esiste e se è verificato.
     *
     * @param id L'ID del prodotto da recuperare.
     * @return Il prodotto se esiste e se è verificato, altrimenti una risposta 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProductById(@PathVariable Long id) {
        return prodottoRepository.findById(id)
                .filter(Prodotto::isState)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Aggiorna un prodotto esistente.
     *
     * @param id L'ID del prodotto da aggiornare.
     * @param prodottoData I dati aggiornati del prodotto.
     * @return Il prodotto aggiornato.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> updateProduct(@PathVariable Long id, @RequestBody Prodotto prodottoData) {
        Prodotto updated = venditoreService.updateProduct(id, prodottoData);
        return ResponseEntity.ok(updated);
    }

    /**
     * Elimina un prodotto dalla piattaforma.
     *
     * @param id L'ID del prodotto da eliminare.
     * @return Una risposta senza contenuto (204) se il prodotto è stato eliminato.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        venditoreService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
