package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.ProdottoProduttore;

import java.util.Date;

public interface IVenditore {

    ProdottoProduttore loadProduct(String name,
                                   double price,
                                   String description,
                                   Date expiration,
                                   String cultivationProcess);

}