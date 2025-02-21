package com.example.filiera_francoletti_belardinelli_raiola.Model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

import java.util.Date;
import java.util.List;

public class Prodotto {
    private String name;
    private double price;
    private String description;
    private Date expiration;
    private Indirizzo processingLocation;
    private boolean state;
    private Venditore seller;
    private int idProduct =0;

    public Prodotto(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.expiration = expiration;
        this.processingLocation = processingLocation;
        this.state = false;
        this.seller = seller;
        this.idProduct++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Indirizzo getProcessingLocation() {
        return processingLocation;
    }

    public void setProcessingLocation(Indirizzo processingLocation) {
        this.processingLocation = processingLocation;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Venditore getSeller() {
        return seller;
    }

    public void setSeller(Venditore seller) {
        this.seller = seller;
    }

    public int getId() {
        return idProduct;
    }

    public void setId(int id) {
        this.idProduct = id;
    }
}
