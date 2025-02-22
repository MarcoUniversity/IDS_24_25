package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.ProdottoDistributore;

import java.util.Date;

public interface IBuilder {

    // Inizializza il builder con i dati base del bundle
    public void startBundle(String bundleName, double price, String description, Date expiration);

    // Aggiunge un sub-prodotto al bundle
    public void addSubProduct(Prodotto subProduct);

    // Completa la costruzione e restituisce il prodotto finale
    public Prodotto finishBundle();
}
