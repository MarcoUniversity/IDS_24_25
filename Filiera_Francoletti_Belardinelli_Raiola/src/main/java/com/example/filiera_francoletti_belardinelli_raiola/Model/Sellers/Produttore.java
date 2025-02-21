package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerInvito;
import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerVenditore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.ProdottoProduttore;

import java.util.Date;

public class Produttore extends Venditore
{
    public Produttore(String name, Indirizzo address, HandlerVenditore handlerProduct, HandlerInvito handlerInvite) {
        super(name, address, handlerProduct, handlerInvite);
    }
    public void loadProduct(String name, double price, String description, Date expiration, String cultivationProcess) {
        this.getHandlerProduct().loadProduct(new ProdottoProduttore(name,price,description,expiration,this.getAddress(),this,cultivationProcess));
    }
}
