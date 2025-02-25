package com.example.filiera_francoletti_belardinelli_raiola.model.Payment;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un carrello di acquisto.
 * Il carrello può contenere una lista di prodotti.
 */
@Entity
public class Carrello {

    /**
     * Identificativo univoco del carrello, generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Lista di prodotti presenti nel carrello.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Prodotto> products = new ArrayList<>();

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Carrello() {}

    /**
     * Restituisce la lista di prodotti nel carrello.
     *
     * @return Lista di prodotti presenti nel carrello.
     */
    public List<Prodotto> getProducts() { return products; }

    /**
     * Aggiunge un prodotto al carrello.
     *
     * @param product Prodotto da aggiungere al carrello.
     */
    public void addProduct(Prodotto product) {
        if (product != null) {
            products.add(product);
        }
    }

    /**
     * Rimuove un prodotto dal carrello in base al suo ID.
     *
     * @param id ID del prodotto da rimuovere.
     */
    public void removeProduct(Long id) {
        products.removeIf(product -> product.getId().intValue() == id);
    }

    /**
     * Restituisce l'ID del carrello.
     *
     * @return ID univoco del carrello.
     */
    public Long getId() { return id; }

    /**
     * Imposta l'ID del carrello.
     *
     * @param id Nuovo ID del carrello.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Svuota il carrello rimuovendo tutti i prodotti.
     */
    public void clearProducts() { this.products.clear(); }
}
