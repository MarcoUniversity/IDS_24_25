package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Carrello;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Pagamento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Ricevuta;

import java.util.List;

public class HandlerAcquirente {
    private Carrello shoppingCart;
    private List<Pagamento> payments;

    public HandlerAcquirente(Carrello shoppingCart, List<Pagamento> payments) {
        this.shoppingCart = shoppingCart;
        this.payments = payments;
    }

    public void addProduct(int id) {
        shoppingCart.addProduct(id);
    }

    /*public Pagamento pay() {
        Pagamento pagamento = new Pagamento(this, shoppingCart, new Ricevuta("Invoice for purchase"));
        payments.add(pagamento);
        return pagamento;
    }*/

    public void removeProduct(int id) {
        shoppingCart.removeProduct(id);
    }

    public Carrello getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Carrello shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Pagamento> getPayments() {
        return payments;
    }

    public void setPayments(List<Pagamento> payments) {
        this.payments = payments;
    }
}

