package com.example.filiera_francoletti_belardinelli_raiola.model.social;

import com.example.filiera_francoletti_belardinelli_raiola.model.product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.sellers.Venditore;
import jakarta.persistence.*;

/**
 * Rappresenta un contenuto social, associato a un prodotto e a un venditore, con una descrizione.
 */
@Entity
public class ContenutoSocial {

    /**
     * Identificativo univoco del contenuto social.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Prodotto associato al contenuto social.
     */
    @ManyToOne
    private Prodotto product;

    /**
     * Venditore associato al contenuto social.
     */
    @ManyToOne
    private Venditore seller;

    /**
     * Descrizione del contenuto social.
     */
    private String description;

    /**
     * Costruttore di default.
     */
    public ContenutoSocial() {}

    /**
     * Costruttore con parametri.
     *
     * @param product     il prodotto associato al contenuto
     * @param seller      il venditore associato al contenuto
     * @param description la descrizione del contenuto
     */
    public ContenutoSocial(Prodotto product, Venditore seller, String description) {
        this.product = product;
        this.seller = seller;
        this.description = description;
    }

    /**
     * Restituisce l'ID del contenuto social.
     *
     * @return l'ID del contenuto
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID del contenuto social.
     *
     * @param id l'ID da impostare
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il prodotto associato al contenuto social.
     *
     * @return il prodotto associato
     */
    public Prodotto getProduct() {
        return product;
    }

    /**
     * Imposta il prodotto associato al contenuto social.
     *
     * @param product il prodotto da associare
     */
    public void setProduct(Prodotto product) {
        this.product = product;
    }

    /**
     * Restituisce il venditore associato al contenuto social.
     *
     * @return il venditore associato
     */
    public Venditore getSeller() {
        return seller;
    }

    /**
     * Imposta il venditore associato al contenuto social.
     *
     * @param seller il venditore da associare
     */
    public void setSeller(Venditore seller) {
        this.seller = seller;
    }

    /**
     * Restituisce la descrizione del contenuto social.
     *
     * @return la descrizione del contenuto
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione del contenuto social.
     *
     * @param description la descrizione da impostare
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
