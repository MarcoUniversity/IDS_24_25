package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.model.Social.ContenutoSocial;
import com.example.filiera_francoletti_belardinelli_raiola.model.Social.Social;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandlerVenditore {
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public HandlerVenditore(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    /* Crea un prodotto per il venditore utilizzando il metodo factory dell'entità
    public Prodotto createProductForVenditore(Venditore venditore, String name, double price, String description, java.util.Date expiration) {
        Prodotto prodotto = venditore.createProduct(name, price, description, expiration);
        return prodottoRepository.save(prodotto);
    }*/

    // NUOVO METODO: crea un prodotto per il venditore
    public Prodotto createProductForVenditore(Venditore venditore, String name, double price, String description, java.util.Date expiration) {
        if (venditore == null) {
            throw new RuntimeException("Venditore mancante!");
        }
        // Usa il metodo factory definito nella classe Venditore (sottoclasse)
        Prodotto prodotto = venditore.createProduct(name, price, description, expiration);
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

