package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandlerCuratore {

    private final ProdottoRepository prodottoRepository;

    @Autowired
    public HandlerCuratore(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    // Restituisce tutti i prodotti non verificati (state == false)
    public List<Prodotto> getPendingProducts() {
        return prodottoRepository.findByStateFalse();
    }

    // Imposta state a true per il prodotto indicato e lo salva
    public Prodotto verifyProduct(Long productId) {
        Prodotto product = prodottoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + productId));
        product.setState(true);
        return prodottoRepository.save(product);
    }

    // Restituisce i prodotti verificati per un dato venditore
    public List<Prodotto> getVerifiedProductsByVendor(Long vendorId) {
        return prodottoRepository.findBySellerIdAndStateTrue(vendorId);
    }
}
