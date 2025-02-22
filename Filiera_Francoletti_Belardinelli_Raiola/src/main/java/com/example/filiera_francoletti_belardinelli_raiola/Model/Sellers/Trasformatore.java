package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.ProdottoTrasformatore;

import java.util.Date;

public class Trasformatore extends Venditore{

    private String transformationProcess;

    public Trasformatore(String name, Indirizzo address,String transformationProcess) {
        super(name, address);
        this.transformationProcess = transformationProcess;
    }

    @Override
    protected Prodotto createProduct(String name, double price, String description, Date expiration) {
        return new ProdottoTrasformatore(name, price, description, expiration, getAddress(), this, transformationProcess);
    }

    public String getTransformationProcess() {
        return transformationProcess;
    }

    public void setTransformationProcess(String transformationProcess) {
        this.transformationProcess = transformationProcess;
    }

}


