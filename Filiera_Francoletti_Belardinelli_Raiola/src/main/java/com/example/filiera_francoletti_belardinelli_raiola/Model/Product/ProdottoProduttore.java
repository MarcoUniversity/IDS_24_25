package com.example.filiera_francoletti_belardinelli_raiola.Model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

import java.util.Date;

public class ProdottoProduttore extends Prodotto {
    private String cultivationProcess;

    public ProdottoProduttore(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller, String  cultivationProcess) {
        super(name, price, description, expiration, processingLocation, seller);
        this.cultivationProcess = cultivationProcess;
    }

    public String getCultivationProcess() {
        return cultivationProcess;
    }

    public void setCultivationProcess(String cultivationProcess) {
        this.cultivationProcess = cultivationProcess;
    }
}