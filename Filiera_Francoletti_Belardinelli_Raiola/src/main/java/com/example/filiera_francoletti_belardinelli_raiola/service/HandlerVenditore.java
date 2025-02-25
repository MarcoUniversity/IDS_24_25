package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.model.social.ContenutoSocial;
import com.example.filiera_francoletti_belardinelli_raiola.model.social.Social;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service per la gestione dei venditori e dei prodotti associati.
 */
@Service
public class HandlerVenditore {
    private final ProdottoRepository prodottoRepository;

    /**
     * Costruttore che inizializza il repository dei prodotti.
     *
     * @param prodottoRepository Repository per la gestione dei prodotti.
     */
    @Autowired
    public HandlerVenditore(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    /**
     * Crea un nuovo prodotto associato a un venditore.
     *
     * @param venditore          Il venditore che crea il prodotto.
     * @param name               Nome del prodotto.
     * @param price              Prezzo del prodotto.
     * @param description        Descrizione del prodotto.
     * @param expiration         Data di scadenza del prodotto.
     * @param processingLocation Indirizzo della lavorazione del prodotto.
     * @return Il prodotto creato e salvato nel database.
     * @throws RuntimeException Se il venditore o il processingLocation sono null.
     */
    public Prodotto createProductForVenditore(Venditore venditore, String name, double price, String description, Date expiration, Indirizzo processingLocation) {
        if (venditore == null) {
            throw new RuntimeException("Venditore mancante!");
        }
        if (processingLocation == null) {
            throw new RuntimeException("processingLocation is required!");
        }
        Prodotto prodotto = venditore.createProduct(name, price, description, expiration);
        prodotto.setProcessingLocation(processingLocation);
        return prodottoRepository.save(prodotto);
    }

    /**
     * Aggiorna le informazioni di un prodotto esistente.
     *
     * @param id          ID del prodotto da aggiornare.
     * @param updatedData Nuovi dati del prodotto.
     * @return Il prodotto aggiornato.
     * @throws RuntimeException Se il prodotto non viene trovato nel database.
     */
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

    /**
     * Elimina un prodotto dal database.
     *
     * @param id ID del prodotto da eliminare.
     */
    public void deleteProduct(Long id) {
        prodottoRepository.deleteById(id);
    }

    /**
     * Recupera un prodotto in base al suo ID.
     *
     * @param id ID del prodotto da recuperare.
     * @return Il prodotto corrispondente, o null se non trovato.
     */
    public Prodotto getProductById(Long id) {
        return prodottoRepository.findById(id).orElse(null);
    }

    /**
     * Recupera tutti i prodotti presenti nel database.
     *
     * @return Lista di tutti i prodotti.
     */
    public List<Prodotto> getAllProducts() {
        return prodottoRepository.findAll();
    }

    /**
     * Aggiunge un contenuto social solo se il prodotto associato è verificato.
     *
     * @param socialContent Il contenuto social da aggiungere.
     * @return Il contenuto social aggiunto.
     * @throws IllegalArgumentException Se il prodotto non ha un ID valido.
     * @throws IllegalStateException Se il prodotto non è verificato.
     */
    public ContenutoSocial addSocialContent(ContenutoSocial socialContent) {
        if (socialContent.getProduct() == null || socialContent.getProduct().getId() == null) {
            throw new IllegalArgumentException("Il prodotto deve essere fornito con un ID valido");
        }
        Prodotto productFromDb = prodottoRepository.findById(socialContent.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + socialContent.getProduct().getId()));

        if (!productFromDb.isState()) {
            throw new IllegalStateException("Il prodotto non è verificato: impossibile aggiungere contenuto social");
        }
        socialContent.setProduct(productFromDb);
        Social.getSocial().addSocialAdvertisement(socialContent);
        return socialContent;
    }
}
