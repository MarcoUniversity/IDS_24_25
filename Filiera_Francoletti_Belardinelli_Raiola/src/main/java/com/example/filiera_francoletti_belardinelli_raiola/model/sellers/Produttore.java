package com.example.filiera_francoletti_belardinelli_raiola.model.sellers;

import com.example.filiera_francoletti_belardinelli_raiola.model.map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.product.ProdottoProduttore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

/**
 * Classe che rappresenta un produttore, che è un tipo specifico di venditore.
 * Un produttore è responsabile della creazione di prodotti con un processo di coltivazione specifico.
 */
@Entity
@DiscriminatorValue("PRODUTTORE")
public class Produttore extends Venditore {

    /**
     * Processo di coltivazione associato al produttore.
     */
    private String cultivationProcess;

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Produttore() {
        super();
    }

    /**
     * Costruttore che inizializza un produttore con i dettagli forniti.
     *
     * @param name Nome del produttore.
     * @param address Indirizzo del produttore.
     * @param cultivationProcess Processo di coltivazione del produttore.
     */
    public Produttore(String name, Indirizzo address, String cultivationProcess) {
        super(name, address);
        this.cultivationProcess = cultivationProcess;
    }

    /**
     * Crea un nuovo prodotto associato al produttore.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     * @return Un'istanza di {@link ProdottoProduttore} con i dettagli specificati.
     */
    @Override
    public Prodotto createProduct(String name, double price, String description, Date expiration) {
        return new ProdottoProduttore(name, price, description, expiration, getAddress(), this, cultivationProcess);
    }

    /**
     * Restituisce il processo di coltivazione del produttore.
     *
     * @return Processo di coltivazione.
     */
    public String getCultivationProcess() {
        return cultivationProcess;
    }

    /**
     * Imposta il processo di coltivazione del produttore.
     *
     * @param cultivationProcess Nuovo processo di coltivazione.
     */
    public void setCultivationProcess(String cultivationProcess) {
        this.cultivationProcess = cultivationProcess;
    }
}
