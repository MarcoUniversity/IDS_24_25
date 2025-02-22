package com.example.filiera_francoletti_belardinelli_raiola.Model.Payment;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerCarrello;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class Carrello {
    private HandlerCarrello shoppingCartHandler;

    public Carrello() {

        this.shoppingCartHandler = new HandlerCarrello();
    }

    public List<Prodotto> getProducts() {
        return this.shoppingCartHandler.getProducts();
    }

    public Prodotto getProductById(int id) {
        return this.shoppingCartHandler.getProductById(id);
    }

    public void addProduct(int id) {
        this.shoppingCartHandler.addProduct(id);
    }

    public void removeProduct(int id) {
        this.shoppingCartHandler.removeProduct(id);
    }

    public HandlerCarrello getShoppingCartHandler() {
        return this.shoppingCartHandler;
    }
}

