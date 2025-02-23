package com.example.filiera_francoletti_belardinelli_raiola.Model.Administration;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Mappa;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.ArrayList;
import java.util.List;

public class Piattaforma {
    private List<Prodotto> productInPlatform;
    private List<Evento> eventInPlatform;
    private static Piattaforma instancePlatform;

    private Piattaforma() {
        productInPlatform = new ArrayList<>();
        eventInPlatform = new ArrayList<>();
    }

    public static synchronized Piattaforma getPlatform() {
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

        Mappa map= Mappa.getMap();
        map.addIndirizzo(product.getProcessingLocation());
        productInPlatform.add(product);
    }

    public void removeProduct(int id) {
        productInPlatform.removeIf(product -> product.getId() == id);
    }

    public void addEventInPlatform(Evento event) {
        eventInPlatform.add(event);
        Mappa map= Mappa.getMap();
        map.addIndirizzo(event.getPlace());
    }

    public void removeEvent(int id) {
        eventInPlatform.removeIf(event -> event.getId() == id);
    }

    public Prodotto getProductByID(Long id) {
        for (Prodotto product : productInPlatform) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Evento getEventByID(Long id) {
        for (Evento event : eventInPlatform) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }


}

