package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerAcquirente;

public class Acquirente extends UtenteGenerico{
    private HandlerAcquirente buyerHandler;

    public Acquirente(String name, String email, String password) {
        super(name, email, password);
        this.buyerHandler =  new HandlerAcquirente();
    }

    public void addProduct(int id) {

        this.buyerHandler.addProduct(id);
    }

    public Ricevuta pay() {

        return this.buyerHandler.pay(this).getInvoice();
    }

    public void removeProduct(int id) {

        this.buyerHandler.removeProduct(id);
    }

    public HandlerAcquirente getBuyerHandler() {
        return buyerHandler;
    }

}
