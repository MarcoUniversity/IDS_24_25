package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.model.Social.ContenutoSocial;
import com.example.filiera_francoletti_belardinelli_raiola.model.Social.Social;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HandlerVenditore {
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public HandlerVenditore(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    // Metodo aggiornato: include processingLocation come parametro
    public Prodotto createProductForVenditore(Venditore venditore, String name, double price, String description, Date expiration, Indirizzo processingLocation) {
        if (venditore == null) {
            throw new RuntimeException("Venditore mancante!");
        }
        if (processingLocation == null) {
            throw new RuntimeException("processingLocation is required!");
        }
        // Usa il metodo factory definito nella classe Venditore (o nelle sue sottoclassi)
        Prodotto prodotto = venditore.createProduct(name, price, description, expiration);
        // Imposta il processingLocation ricevuto dal JSON
        prodotto.setProcessingLocation(processingLocation);
        return prodottoRepository.save(prodotto);
    }

    public Prodotto updateProduct(Long id, Prodotto updatedData) {
        Prodotto existing = prodottoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + id));
        existing.setName(updatedData.getName());
        existing.setPrice(updatedData.getPrice());
        existing.setDescription(updatedData.getDescription());
        existing.setExpiration(updatedData.getExpiration());
        existing.setProcessingLocation(updatedData.getProcessingLocation());
        return prodottoRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        prodottoRepository.deleteById(id);
    }

    public Prodotto getProductById(Long id) {
        return prodottoRepository.findById(id).orElse(null);
    }

    public List<Prodotto> getAllProducts() {
        return prodottoRepository.findAll();
    }


   /* public void socialPromotion(String description, Prodotto product, Venditore seller) {
        if (product != null && seller != null && description != null) {
            // Se Social è gestito come bean, lo iniettiamo; altrimenti usiamo il metodo statico.
            Social social = Social.getSocial();
            social.addSocialAdvertisement(new ContenutoSocial(product, seller, description));
        }
    }*/
}

