package com.example.filiera_francoletti_belardinelli_raiola.model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("TRASFORMATORE")
public class ProdottoTrasformatore extends Prodotto {

    private String transformationProcess;

    public ProdottoTrasformatore(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller, String transformationProcess) {
        super(name, price, description, expiration, processingLocation, seller);
        this.transformationProcess = transformationProcess;
    }

    public ProdottoTrasformatore() {

    }

    public String getTransformationProcess() {
        return transformationProcess;
    }

    public void setTransformationProcess(String transformationProcess) {
        this.transformationProcess = transformationProcess;
    }
}
