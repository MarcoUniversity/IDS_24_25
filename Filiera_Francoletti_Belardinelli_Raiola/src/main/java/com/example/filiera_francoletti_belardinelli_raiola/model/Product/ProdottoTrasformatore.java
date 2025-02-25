package com.example.filiera_francoletti_belardinelli_raiola.model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

/**
 * Classe che rappresenta un prodotto trasformato da un trasformatore.
 * Estende la classe {@link Prodotto} e aggiunge informazioni sul processo di trasformazione.
 */
@Entity
@DiscriminatorValue("TRASFORMATORE")
public class ProdottoTrasformatore extends Prodotto {

    /**
     * Descrizione del processo di trasformazione del prodotto.
     */
    private String transformationProcess;

    /**
     * Costruttore che inizializza un prodotto trasformatore con i dettagli forniti.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     * @param processingLocation Indirizzo della lavorazione del prodotto.
     * @param seller Venditore associato al prodotto.
     * @param transformationProcess Processo di trasformazione del prodotto.
     */
    public ProdottoTrasformatore(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller, String transformationProcess) {
        super(name, price, description, expiration, processingLocation, seller);
        this.transformationProcess = transformationProcess;
    }

    /**
     * Costruttore di default richiesto da JPA.
     */
    public ProdottoTrasformatore() {}

    /**
     * Restituisce il processo di trasformazione del prodotto.
     *
     * @return Il processo di trasformazione.
     */
    public String getTransformationProcess() {
        return transformationProcess;
    }

    /**
     * Imposta il processo di trasformazione del prodotto.
     *
     * @param transformationProcess Nuovo processo di trasformazione.
     */
    public void setTransformationProcess(String transformationProcess) {
        this.transformationProcess = transformationProcess;
    }
}
