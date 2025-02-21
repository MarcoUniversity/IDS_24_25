package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

public class ContenutoSocial {
    private Prodotto product;
    private Venditore seller;
    private String description;

    public ContenutoSocial(Prodotto product, Venditore seller, String description) {
        this.product = product;
        this.seller = seller;
        this.description = description;
    }

    public Prodotto getProduct() {
        return product;
    }

    public void setProduct(Prodotto product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Venditore getSeller() {
        return seller;
    }

    public void setSeller(Venditore seller) {
        this.seller = seller;
    }
}
