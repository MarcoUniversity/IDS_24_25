package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.Date;

public interface IVenditore {
    void loadProduct(String name, double price, String description, Date expiration);
}
