package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import java.util.List;

public class UtenteGenerico implements Subscriber {
    private String name;
    private String email;
    private String password;

    public UtenteGenerico(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void update() {
        // Update logic here
    }

    public List<Prodotto> viewProducts() {
        Piattaforma pf=Piattaforma.getPlatform();
        return pf.getProductInPlatform();
    }

    public List<Evento> viewEvents() {
        Piattaforma pf=Piattaforma.getPlatform();
        return pf.getEventInPlatform();
    }

    public Mappa viewMap() {
        Mappa map=Mappa.getMap();
        return map.getMap();
    }

    public Indirizzo traceProduct(int id) {
        Piattaforma pf=Piattaforma.getPlatform();
        return pf.getProductByID(id).getProcessingLocation();
    }
}

