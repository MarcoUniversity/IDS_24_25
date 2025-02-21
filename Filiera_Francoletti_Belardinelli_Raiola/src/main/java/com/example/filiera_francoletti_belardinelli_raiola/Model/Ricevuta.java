package com.example.filiera_francoletti_belardinelli_raiola.Model;

public class Ricevuta {
    private String invoice;

    public Ricevuta(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}

