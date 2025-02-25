package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Administration.Piattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Carrello;
import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Pagamento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Ricevuta;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Acquirente;
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

    public void addProduct(Long id) {
        Piattaforma pt = Piattaforma.getPlatform();
        Prodotto p = pt.getProductByID(id);
        this.shoppingCart.addProduct(p);
    }

    public Pagamento pay(Acquirente payer) {
        Pagamento pagamento = new Pagamento(payer, this.shoppingCart, new Ricevuta("Invoice for purchase"));
        this.payments.add(pagamento);
        return pagamento;
    }

    public void removeProduct(Long id) {
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

