package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Mappa;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Piattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class HandlerUtente {

    public HandlerUtente() {}

    public List<Prodotto> viewProducts() {

        Piattaforma pf = Piattaforma.getPlatform();
        return pf.getProductInPlatform();
    }

    public List<Evento> viewEvents() {
        Piattaforma pf = Piattaforma.getPlatform();
        return pf.getEventInPlatform();
    }

    public Mappa viewMap() {
        Mappa map=Mappa.getMap();
        return map;
    }

    public Indirizzo traceProduct(int id) {
        Piattaforma pf = Piattaforma.getPlatform();
        Prodotto prod = pf.getProductByID(id);
        if (prod != null) {
            return prod.getProcessingLocation();
        }
        return null;
    }

}

