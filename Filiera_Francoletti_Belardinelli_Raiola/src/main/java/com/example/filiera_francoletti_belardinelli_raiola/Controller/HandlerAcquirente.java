package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Users.Acquirente;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Payment.Carrello;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Payment.Pagamento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Payment.Ricevuta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandlerAcquirente {
    private Carrello shoppingCart;
    private List<Pagamento> payments;

    public HandlerAcquirente() {
        this.shoppingCart = new Carrello();
        this.payments = new ArrayList<>();
    }

    public void addProduct(int id) {
        this.shoppingCart.addProduct(id);
    }

    public Pagamento pay(Acquirente payer) {
        Pagamento pagamento = new Pagamento(payer, shoppingCart, new Ricevuta("Invoice for purchase"));
        this.payments.add(pagamento);
        return pagamento;
    }

    public void removeProduct(int id) {
        this.shoppingCart.removeProduct(id);
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

