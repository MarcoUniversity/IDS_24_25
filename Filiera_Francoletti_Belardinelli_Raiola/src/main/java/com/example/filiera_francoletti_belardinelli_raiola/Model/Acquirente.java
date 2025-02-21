package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerAcquirente;

import java.util.List;

public class Acquirente {
    private HandlerAcquirente buyerHandler;

    public Acquirente(HandlerAcquirente buyerHandler) {
        this.buyerHandler = buyerHandler;
    }

    public void addProduct(int id) {
        buyerHandler.addProduct(id);
    }

    public Ricevuta pay() {
        return buyerHandler.pay(this).getInvoice();
    }

    public void removeProduct(int id) {
        buyerHandler.removeProduct(id);
    }

    public Carrello getShoppingCart() {
        return buyerHandler.getShoppingCart();
    }

    public List<Pagamento> getPayments() {
        return buyerHandler.getPayments();
    }
}

