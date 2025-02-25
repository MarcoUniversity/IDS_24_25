package com.example.filiera_francoletti_belardinelli_raiola.model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

/**
 * Classe che rappresenta un prodotto prodotto da un produttore.
 * Estende la classe {@link Prodotto} e aggiunge informazioni sul processo di coltivazione.
 */
@Entity
@DiscriminatorValue("PRODUTTORE")
public class ProdottoProduttore extends Prodotto {

    /**
     * Descrizione del processo di coltivazione del prodotto.
     */
    private String cultivationProcess;

    /**
     * Costruttore che inizializza un prodotto produttore con i dettagli forniti.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     * @param processingLocation Indirizzo della lavorazione del prodotto.
     * @param seller Venditore associato al prodotto.
     * @param cultivationProcess Processo di coltivazione del prodotto.
     */
    public ProdottoProduttore(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller, String cultivationProcess) {
        super(name, price, description, expiration, processingLocation, seller);
        this.cultivationProcess = cultivationProcess;
    }

    /**
     * Costruttore di default richiesto da JPA.
     */
    public ProdottoProduttore() {}

    /**
     * Restituisce il processo di coltivazione del prodotto.
     *
     * @return Il processo di coltivazione.
     */
    public String getCultivationProcess() {
        return cultivationProcess;
    }

    /**
     * Imposta il processo di coltivazione del prodotto.
     *
     * @param cultivationProcess Nuovo processo di coltivazione.
     */
    public void setCultivationProcess(String cultivationProcess) {
        this.cultivationProcess = cultivationProcess;
    }
}