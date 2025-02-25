package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Social.ContenutoSocial;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandlerSocial {
    private final SocialRepository socialRepository;
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public HandlerSocial(SocialRepository socialRepository, ProdottoRepository prodottoRepository) {
        this.socialRepository = socialRepository;
        this.prodottoRepository = prodottoRepository;
    }

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

    public List<ContenutoSocial> getAllSocialContent() {
        return socialRepository.findAll();
    }

    public ContenutoSocial getSocialContentById(Long id) {
        return socialRepository.findById(id).orElse(null);
    }

    public ContenutoSocial updateSocialContent(Long id, ContenutoSocial updatedContent) {
        return socialRepository.findById(id).map(cs -> {
            cs.setDescription(updatedContent.getDescription());
            cs.setProduct(updatedContent.getProduct());
            cs.setSeller(updatedContent.getSeller());
            return socialRepository.save(cs);
        }).orElseThrow(() -> new RuntimeException("Contenuto social non trovato con id: " + id));
    }

    public void deleteSocialContent(Long id) {
        socialRepository.deleteById(id);
    }
}
