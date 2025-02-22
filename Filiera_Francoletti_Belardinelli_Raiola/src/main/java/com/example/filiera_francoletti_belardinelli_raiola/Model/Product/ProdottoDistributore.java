package com.example.filiera_francoletti_belardinelli_raiola.Model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProdottoDistributore extends Prodotto{

    private List<Prodotto>listOfProduct;

    public ProdottoDistributore(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller,List<Prodotto>listOfProduct) {
        super(name, price, description, expiration, processingLocation, seller);
        if (listOfProduct == null) {
            this.listOfProduct = new ArrayList<>();
        } else {
            this.listOfProduct = listOfProduct;
        }
    }
    public void addProdotto(Prodotto prodotto) {
        if (prodotto != null) {
            this.listOfProduct.add(prodotto);
        }
    }
    public List<Prodotto> getListOfProduct() {
        return listOfProduct;
    }
    public void setListOfProduct(List<Prodotto> listOfProduct) {
        this.listOfProduct = listOfProduct;
    }
}
