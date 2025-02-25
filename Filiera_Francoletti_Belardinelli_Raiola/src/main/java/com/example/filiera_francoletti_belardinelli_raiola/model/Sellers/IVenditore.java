package com.example.filiera_francoletti_belardinelli_raiola.model.Sellers;

import java.util.Date;

/**
 * Interfaccia che definisce le operazioni di caricamento di un prodotto da parte di un venditore.
 */
public interface IVenditore {

    /**
     * Carica un nuovo prodotto con le informazioni specificate.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     */
    void loadProduct(String name, double price, String description, Date expiration);
}