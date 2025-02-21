package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerPiattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class Piattaforma {
    private List<Prodotto> productInPlatform;
    private List<Evento> eventInPlatform;
    private static Piattaforma instancePlatform;

    private Piattaforma() {}

    public static Piattaforma getPlatform() {
        if (instancePlatform == null) {
            instancePlatform = new Piattaforma();
        }
        return instancePlatform;
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

    public Prodotto getProductByID(int id) {
        for (Prodotto product : productInPlatform) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Evento getEventByID(int id) {
        for (Evento event : eventInPlatform) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }


}

