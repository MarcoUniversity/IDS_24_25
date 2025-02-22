package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerInvito;
import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerVenditore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.ProdottoProduttore;

import java.util.Date;

public class Produttore extends Venditore
{
    private String cultivationProcess;

    public Produttore(String name, Indirizzo address,String cultivationProcess) {
        super(name, address);
        this.cultivationProcess = cultivationProcess;
    }
    @Override
    protected Prodotto createProduct(String name, double price, String description, Date expiration) {
        return new ProdottoProduttore(name, price, description, expiration, getAddress(), this, cultivationProcess);
    }
    public String getCultivationProcess() {
        return cultivationProcess;
    }

    public void setCultivationProcess(String cultivationProcess) {
        this.cultivationProcess = cultivationProcess;
    }
}
