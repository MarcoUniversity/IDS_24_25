package com.example.filiera_francoletti_belardinelli_raiola.model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.ProdottoTrasformatore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

/**
 * Classe che rappresenta un trasformatore, un tipo specifico di venditore.
 * Un trasformatore è responsabile della creazione di prodotti tramite un processo di trasformazione.
 */
@Entity
@DiscriminatorValue("TRASFORMATORE")
public class Trasformatore extends Venditore {

    /**
     * Descrizione del processo di trasformazione associato al trasformatore.
     */
    private String transformationProcess;

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Trasformatore() {
        super();
    }

    /**
     * Costruttore che inizializza un trasformatore con i dettagli forniti.
     *
     * @param name Nome del trasformatore.
     * @param address Indirizzo del trasformatore.
     * @param transformationProcess Processo di trasformazione del trasformatore.
     */
    public Trasformatore(String name, Indirizzo address, String transformationProcess) {
        super(name, address);
        this.transformationProcess = transformationProcess;
    }

    /**
     * Crea un nuovo prodotto associato al trasformatore.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     * @return Un'istanza di {@link ProdottoTrasformatore} con i dettagli specificati.
     */
    @Override
    public Prodotto createProduct(String name, double price, String description, Date expiration) {
        return new ProdottoTrasformatore(name, price, description, expiration, getAddress(), this, transformationProcess);
    }

    /**
     * Restituisce il processo di trasformazione del trasformatore.
     *
     * @return Processo di trasformazione.
     */
    public String getTransformationProcess() {
        return transformationProcess;
    }

    /**
     * Imposta il processo di trasformazione del trasformatore.
     *
     * @param transformationProcess Nuovo processo di trasformazione.
     */
    public void setTransformationProcess(String transformationProcess) {
        this.transformationProcess = transformationProcess;
    }
}
