package com.example.filiera_francoletti_belardinelli_raiola.Model.Users;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerUtente;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Mappa;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class UtenteGenerico implements Subscriber {
    private String name;
    private String email;
    private String password;
    private HandlerUtente userHandler;

    public UtenteGenerico(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userHandler=new HandlerUtente();
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
        System.out.println("Nuovo evento in piattaforma!");
    }

    public List<Prodotto> viewProducts() {
        return userHandler.viewProducts();

    }

    public List<Evento> viewEvents() {
        return userHandler.viewEvents();

    }

    public Mappa viewMap() {
        return userHandler.viewMap();

    }

    public Indirizzo traceProduct(int id) {
        return userHandler.traceProduct(id);
    }
}

