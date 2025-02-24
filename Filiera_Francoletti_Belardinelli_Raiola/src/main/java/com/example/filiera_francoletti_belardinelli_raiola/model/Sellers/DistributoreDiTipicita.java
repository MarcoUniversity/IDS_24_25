package com.example.filiera_francoletti_belardinelli_raiola.model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.ProdottoDistributore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.Date;

@Entity
@DiscriminatorValue("DISTRIBUTORE")
public class DistributoreDiTipicita extends Venditore implements IBuilder{

    @Transient
    private ProdottoDistributore currentBundle = null;

    public DistributoreDiTipicita() {
        super();
    }
    public DistributoreDiTipicita(String name, Indirizzo address) {
        super(name, address);
    }

    public void startBundle(String bundleName, double price, String description, Date expiration) {
        this.currentBundle = new ProdottoDistributore(bundleName, price, description, expiration, getAddress(), this, null);
    }

    public void addSubProduct(Prodotto subProduct) {
        if (this.currentBundle != null && subProduct != null) {
            this.currentBundle.addProdotto(subProduct);
        } else {
            System.out.println("Errore: nessun bundle in corso oppure subProduct nullo.");
        }
    }

    public Prodotto finishBundle() {
     /*   Prodotto finishedBundle = this.currentBundle;
        this.currentBundle = null;
        if (finishedBundle != null) {
            getHandlerProduct().loadProduct(finishedBundle);
        }*/
        return null;
    }

    @Override
    public Prodotto createProduct(String name, double price, String description, Date expiration) {
        return new ProdottoDistributore(name, price, description, expiration, getAddress(), this, null);
    }
}
