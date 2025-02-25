package com.example.filiera_francoletti_belardinelli_raiola.model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;

import java.util.Date;

/**
 * Interfaccia per la costruzione di bundle di prodotti.
 * Definisce i metodi per avviare, aggiungere prodotti e completare un bundle.
 */
public interface IBuilder {

    /**
     * Inizializza la costruzione di un bundle di prodotti con i dati di base.
     *
     * @param bundleName Nome del bundle.
     * @param price Prezzo del bundle.
     * @param description Descrizione del bundle.
     * @param expiration Data di scadenza del bundle.
     */
    void startBundle(String bundleName, double price, String description, Date expiration);

    /**
     * Aggiunge un sottoprodotto al bundle in costruzione.
     *
     * @param subProduct Prodotto da aggiungere al bundle.
     */
    void addSubProduct(Prodotto subProduct);

    /**
     * Conclude la costruzione del bundle e restituisce il prodotto finale.
     *
     * @return Il prodotto finale del bundle.
     */
    Prodotto finishBundle();
}