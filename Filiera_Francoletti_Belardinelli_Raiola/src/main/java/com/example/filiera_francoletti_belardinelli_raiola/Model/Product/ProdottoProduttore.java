package com.example.filiera_francoletti_belardinelli_raiola.Model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

import java.util.Date;
import java.util.List;

public class ProdottoProduttore extends Prodotto {
    private List<Indirizzo> cultivationProcess;

    public ProdottoProduttore(String name, double price, String description, Date expiration, List<Indirizzo> processingLocation, Venditore seller, List<Indirizzo>  cultivationProcess) {
        super(name, price, description, expiration, processingLocation, seller);
        this.cultivationProcess = cultivationProcess;
    }

    public List<Indirizzo> getCultivationProcess() {
        return cultivationProcess;
    }

    public void setCultivationProcess(List<Indirizzo> cultivationProcess) {
        this.cultivationProcess = cultivationProcess;
    }
}