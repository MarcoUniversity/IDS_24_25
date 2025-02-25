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
        Prodotto product = Piattaforma.getPlatform().getProductByID(id);
        if (product == null) {
            throw new RuntimeException("Prodotto non trovato con id: " + id);
        }
        this.shoppingCart.addProduct(product);
    }

    public Pagamento pay(Acquirente payer) {
        String invoiceContent = generateInvoice(this.shoppingCart);
        Ricevuta invoice = new Ricevuta(invoiceContent);
        Pagamento pagamento = new Pagamento(payer, this.shoppingCart, invoice);
        this.payments.add(pagamento);
        this.shoppingCart.clearProducts();
        return pagamento;
    }

    private String generateInvoice(Carrello cart) {
        double total = 0;
        StringBuilder sb = new StringBuilder("Ricevuta:\n");
        for (Prodotto p : cart.getProducts()) {
            sb.append("Prodotto: ").append(p.getName())
                    .append(" - Prezzo: ").append(p.getPrice()).append("\n");
            total += p.getPrice();
        }
        sb.append("Totale: ").append(total);
        return sb.toString();
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
        return this.payments;
    }

    public void setPayments(List<Pagamento> payments) {
        this.payments = payments;
    }
}

