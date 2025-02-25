package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.social.ContenutoSocial;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service per la gestione dei contenuti social associati ai prodotti.
 */
@Service
public class HandlerSocial {
    private final SocialRepository socialRepository;
    private final ProdottoRepository prodottoRepository;

    /**
     * Costruttore che inizializza i repository necessari.
     *
     * @param socialRepository Repository per la gestione dei contenuti social.
     * @param prodottoRepository Repository per la gestione dei prodotti.
     */
    @Autowired
    public HandlerSocial(SocialRepository socialRepository, ProdottoRepository prodottoRepository) {
        this.socialRepository = socialRepository;
        this.prodottoRepository = prodottoRepository;
    }

    /**
     * Aggiunge un nuovo contenuto social, associato a un prodotto verificato.
     *
     * @param socialContent Il contenuto social da aggiungere.
     * @return Il contenuto social salvato.
     * @throws IllegalArgumentException Se il prodotto associato non ha un ID valido.
     * @throws IllegalStateException Se il prodotto non è verificato.
     */
    public ContenutoSocial addSocialContent(ContenutoSocial socialContent) {
        if (socialContent.getProduct() == null || socialContent.getProduct().getId() == null) {
            throw new IllegalArgumentException("Il prodotto deve avere un ID valido");
        }

        // Recupera il prodotto dal DB per assicurarsi che i dati siano aggiornati
        Prodotto productFromDb = prodottoRepository.findById(socialContent.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + socialContent.getProduct().getId()));

        // Controlla se il prodotto è verificato
        if (!productFromDb.isState()) {
            throw new IllegalStateException("Il prodotto non è verificato: impossibile aggiungere contenuto social");
        }

        // Usa il prodotto effettivamente presente in DB
        socialContent.setProduct(productFromDb);

        // Salva il contenuto social nel DB
        return socialRepository.save(socialContent);
    }

    /**
     * Recupera tutti i contenuti social disponibili.
     *
     * @return Lista di tutti i contenuti social presenti nel database.
     */
    public List<ContenutoSocial> getAllSocialContent() {
        return socialRepository.findAll();
    }

    /**
     * Recupera un contenuto social specifico in base al suo ID.
     *
     * @param id ID del contenuto social da recuperare.
     * @return Il contenuto social corrispondente all'ID, o null se non trovato.
     */
    public ContenutoSocial getSocialContentById(Long id) {
        return socialRepository.findById(id).orElse(null);
    }

    /**
     * Aggiorna un contenuto social esistente.
     *
     * @param id ID del contenuto social da aggiornare.
     * @param updatedContent Oggetto contenente le nuove informazioni del contenuto social.
     * @return Il contenuto social aggiornato.
     * @throws RuntimeException Se il contenuto social con l'ID specificato non esiste.
     */
    public ContenutoSocial updateSocialContent(Long id, ContenutoSocial updatedContent) {
        return socialRepository.findById(id).map(cs -> {
            cs.setDescription(updatedContent.getDescription());
            cs.setProduct(updatedContent.getProduct());
            cs.setSeller(updatedContent.getSeller());
            return socialRepository.save(cs);
        }).orElseThrow(() -> new RuntimeException("Contenuto social non trovato con id: " + id));
    }

    /**
     * Elimina un contenuto social dal database.
     *
     * @param id ID del contenuto social da eliminare.
     */
    public void deleteSocialContent(Long id) {
        socialRepository.deleteById(id);
    }
}
