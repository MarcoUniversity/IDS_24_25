package com.example.filiera_francoletti_belardinelli_raiola.Model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

import java.util.Date;

public class ProdottoProduttore extends Prodotto {
    private Indirizzo cultivationProcess;

    public ProdottoProduttore(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller, Indirizzo  cultivationProcess) {
        super(name, price, description, expiration, processingLocation, seller);
        this.cultivationProcess = cultivationProcess;
    }

    public Indirizzo getCultivationProcess() {
        return cultivationProcess;
    }

    public void setCultivationProcess(Indirizzo cultivationProcess) {
        this.cultivationProcess = cultivationProcess;
    }
}