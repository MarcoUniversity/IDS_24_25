package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Mappa;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Piattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class HandlerUtente {
    private Piattaforma platform;

    public HandlerUtente(Piattaforma platform) {
        this.platform = platform;
    }

    public List<Prodotto> viewProducts() {
        return platform.getProductInPlatform();
    }

    public List<Evento> viewEvents() {
        return platform.getEventInPlatform();
    }

    public Mappa viewMap() {
        return platform.getMap();
    }

    /*public Indirizzo traceProduct(int id) {
        return platform.getMap().getListOfAddresses();
    }*/

    public Piattaforma getPlatform() {
        return platform;
    }
}

