package com.example.filiera_francoletti_belardinelli_raiola.model.Sellers;

import java.util.Date;

public interface IVenditore {
    void loadProduct(String name, double price, String description, Date expiration);
}
