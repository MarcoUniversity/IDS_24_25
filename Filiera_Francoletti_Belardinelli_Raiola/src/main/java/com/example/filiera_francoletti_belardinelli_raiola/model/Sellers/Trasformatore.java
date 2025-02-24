package com.example.filiera_francoletti_belardinelli_raiola.model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.ProdottoTrasformatore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;
@Entity
@DiscriminatorValue("TRASFORMATORE")
public class Trasformatore extends Venditore{

    private String transformationProcess;

    public Trasformatore() {
        super();
    }

    public Trasformatore(String name, Indirizzo address,String transformationProcess) {
        super(name, address);
        this.transformationProcess = transformationProcess;
    }

    @Override
    public Prodotto createProduct(String name, double price, String description, Date expiration) {
        return new ProdottoTrasformatore(name, price, description, expiration, getAddress(), this, transformationProcess);
    }

    public String getTransformationProcess() {
        return transformationProcess;
    }

    public void setTransformationProcess(String transformationProcess) {
        this.transformationProcess = transformationProcess;
    }

}


