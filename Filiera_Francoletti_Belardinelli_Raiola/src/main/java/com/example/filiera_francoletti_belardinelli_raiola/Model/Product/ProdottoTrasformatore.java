package com.example.filiera_francoletti_belardinelli_raiola.Model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

import java.util.Date;

public class ProdottoTrasformatore extends Prodotto {

    private String transformationProcess;

    public ProdottoTrasformatore(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller, String transformationProcess) {
        super(name, price, description, expiration, processingLocation, seller);
        this.transformationProcess = transformationProcess;
    }
    public String getTransformationProcess() {
        return transformationProcess;
    }

    public void setTransformationProcess(String transformationProcess) {
        this.transformationProcess = transformationProcess;
    }
}
