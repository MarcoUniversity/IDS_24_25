package com.example.filiera_francoletti_belardinelli_raiola.model.product;

import com.example.filiera_francoletti_belardinelli_raiola.model.map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.sellers.Venditore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe che rappresenta un prodotto distribuito da un distributore.
 * Estende la classe {@link Prodotto} e aggiunge una lista di prodotti distribuiti.
 */
@Entity
@DiscriminatorValue("DISTRIBUTORE")
public class ProdottoDistributore extends Prodotto {

    /**
     * Lista di prodotti distribuiti dal distributore.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Prodotto> listOfProduct;

    /**
     * Costruttore che inizializza un prodotto distributore con i dettagli forniti.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     * @param processingLocation Indirizzo della lavorazione del prodotto.
     * @param seller Venditore associato al prodotto.
     * @param listOfProduct Lista dei prodotti distribuiti.
     */
    public ProdottoDistributore(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller, List<Prodotto> listOfProduct) {
        super(name, price, description, expiration, processingLocation, seller);
        if (listOfProduct == null) {
            this.listOfProduct = new ArrayList<>();
        } else {
            this.listOfProduct = listOfProduct;
        }
    }

    /**
     * Costruttore di default richiesto da JPA.
     */
    public ProdottoDistributore() {}

    /**
     * Aggiunge un prodotto alla lista di prodotti distribuiti.
     *
     * @param prodotto Prodotto da aggiungere alla lista.
     */
    public void addProdotto(Prodotto prodotto) {
        if (prodotto != null) {
            this.listOfProduct.add(prodotto);
        }
    }

    /**
     * Restituisce la lista dei prodotti distribuiti.
     *
     * @return Lista dei prodotti distribuiti.
     */
    public List<Prodotto> getListOfProduct() {
        return listOfProduct;
    }

    /**
     * Imposta la lista dei prodotti distribuiti.
     *
     * @param listOfProduct Nuova lista di prodotti distribuiti.
     */
    public void setListOfProduct(List<Prodotto> listOfProduct) {
        this.listOfProduct = listOfProduct;
    }
}
