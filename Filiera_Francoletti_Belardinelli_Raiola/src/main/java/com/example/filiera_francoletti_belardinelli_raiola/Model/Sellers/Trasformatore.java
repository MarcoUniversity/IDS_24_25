package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerInvito;
import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerVenditore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.ProdottoTrasformatore;

import java.util.Date;

public class Trasformatore extends Venditore{

    public Trasformatore(String name, Indirizzo address, HandlerVenditore handlerProduct, HandlerInvito handlerInvite) {
        super(name, address, handlerProduct, handlerInvite);
    }
    public void loadProduct(String name, double price, String description, Date expiration, String transformationProcess) {
        this.getHandlerProduct().loadProduct(new ProdottoTrasformatore(name, price, description, expiration, this.getAddress(),this,transformationProcess));
    }

}


