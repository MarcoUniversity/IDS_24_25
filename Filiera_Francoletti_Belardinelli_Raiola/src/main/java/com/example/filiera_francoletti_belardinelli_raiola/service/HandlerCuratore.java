package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service per la gestione della verifica dei prodotti da parte del curatore.
 */
@Service
public class HandlerCuratore {

    private final ProdottoRepository prodottoRepository;

    /**
     * Costruttore che inizializza il repository dei prodotti.
     *
     * @param prodottoRepository Repository per la gestione dei prodotti.
     */
    @Autowired
    public HandlerCuratore(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    /**
     * Restituisce tutti i prodotti non verificati.
     *
     * @return Lista di prodotti con stato non verificato (state == false).
     */
    public List<Prodotto> getPendingProducts() {
        return prodottoRepository.findByStateFalse();
    }

    /**
     * Verifica un prodotto, impostando lo stato a true.
     *
     * @param productId ID del prodotto da verificare.
     * @return Il prodotto aggiornato dopo la verifica.
     * @throws RuntimeException se il prodotto non viene trovato.
     */
    public Prodotto verifyProduct(Long productId) {
        Prodotto product = prodottoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + productId));
        product.setState(true);
        return prodottoRepository.save(product);
    }

    /**
     * Restituisce i prodotti verificati per un determinato venditore.
     *
     * @param vendorId ID del venditore.
     * @return Lista di prodotti verificati appartenenti al venditore.
     */
    public List<Prodotto> getVerifiedProductsByVendor(Long vendorId) {
        return prodottoRepository.findBySellerIdAndStateTrue(vendorId);
    }
}
