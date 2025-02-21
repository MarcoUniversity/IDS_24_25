package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerInvito;
import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerVenditore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.ProdottoDistributore;

import java.util.Date;
import java.util.List;

public class DistributoreDiTipicita extends Venditore{
    public DistributoreDiTipicita(String name, Indirizzo address, HandlerVenditore handlerProduct, HandlerInvito handlerInvite) {
        super(name, address, handlerProduct, handlerInvite);
    }
    public void loadProduct(String name, double price, String description, Date expiration, List<Prodotto> p) {
        this.getHandlerProduct().loadProduct(new ProdottoDistributore(name,price,description,expiration,this.getAddress(),this,p));
    }
}
