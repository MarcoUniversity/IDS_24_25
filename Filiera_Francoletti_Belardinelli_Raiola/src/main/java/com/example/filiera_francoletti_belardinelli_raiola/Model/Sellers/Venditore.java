package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.ProdottoProduttore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Subscriber;

import java.util.Date;

public class Venditore implements IVenditore, Subscriber {


    @Override
    public ProdottoProduttore loadProduct(String name, double price, String description, Date expiration) {
        return null;
    }

    @Override
    public void update() {

    }
}