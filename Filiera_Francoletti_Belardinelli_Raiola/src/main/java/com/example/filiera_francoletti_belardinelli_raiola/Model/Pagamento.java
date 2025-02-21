package com.example.filiera_francoletti_belardinelli_raiola.Model;

public class Pagamento {
    private Acquirente payer;
    private Carrello products;
    private Ricevuta invoice;

    public Pagamento(Acquirente payer, Carrello products, Ricevuta invoice) {
        this.payer = payer;
        this.products = products;
        this.invoice = invoice;
    }

    public Acquirente getPayer() {
        return payer;
    }

    public void setPayer(Acquirente payer) {
        this.payer = payer;
    }

    public Carrello getProducts() {
        return products;
    }

    public void setProducts(Carrello products) {
        this.products = products;
    }

    public Ricevuta getInvoice() {
        return invoice;
    }

    public void setInvoice(Ricevuta invoice) {
        this.invoice = invoice;
    }
}

