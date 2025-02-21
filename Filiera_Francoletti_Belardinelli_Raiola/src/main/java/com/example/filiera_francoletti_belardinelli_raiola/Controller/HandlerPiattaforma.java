package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Piattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.Date;

public class HandlerPiattaforma {
    private Date date;
    private Piattaforma piattaforma;

    public HandlerPiattaforma(Piattaforma piattaforma) {
        this.piattaforma = piattaforma;
        this.date = new Date();
    }

    public void removeExpiredProduct(int id) {
        piattaforma.removeProduct(id);
        System.out.println("Expired product removed: " + id);
    }

    public void removeExpiredEvent(int id) {
        piattaforma.removeEvent(id);
        System.out.println("Expired event removed: " + id);
    }

    public void addProductInPlatform(Prodotto product) {
        piattaforma.addProductInPlatform(product);
    }

    public void removeProduct(int id) {
        piattaforma.removeProduct(id);
    }

    public void addEventInPlatform(Evento event) {
        piattaforma.addEventInPlatform(event);
    }

    public void removeEvent(int id) {
        piattaforma.removeEvent(id);
    }
}

