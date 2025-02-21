package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class Piattaforma {
    private List<Prodotto> productInPlatform;
    private List<Evento> eventInPlatform;
    private Mappa map;
    private static Piattaforma instance;

    private Piattaforma() {}

    public static Piattaforma getPlatform() {
        if (instance == null) {
            instance = new Piattaforma();
        }
        return instance;
    }

    public List<Prodotto> getProductInPlatform() {
        return productInPlatform;
    }

    public void setProductInPlatform(List<Prodotto> productInPlatform) {
        this.productInPlatform = productInPlatform;
    }

    public List<Evento> getEventInPlatform() {
        return eventInPlatform;
    }

    public void setEventInPlatform(List<Evento> eventInPlatform) {
        this.eventInPlatform = eventInPlatform;
    }

    public void addProductInPlatform(Prodotto product) {
        productInPlatform.add(product);
    }

    public void removeProduct(int id) {
        productInPlatform.removeIf(product -> product.getId() == id);
    }

    public void addEventInPlatform(Evento event) {
        eventInPlatform.add(event);
    }

    public void removeEvent(int id) {
        eventInPlatform.removeIf(event -> event.getId() == id);
    }

    public Mappa getMap() {
        return map;
    }

    public void setMap(Mappa map) {
        this.map = map;
    }

}

