package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerCarrello;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class Carrello {
    private HandlerCarrello shoppingCartHandler;

    public Carrello(HandlerCarrello shoppingCartHandler) {
        this.shoppingCartHandler = shoppingCartHandler;
    }

    public List<Prodotto> getProducts() {
        return shoppingCartHandler.getProducts();
    }

    public Prodotto getProductById(int id) {
        return shoppingCartHandler.getProductById(id);
    }

    public void addProduct(int id) {
        shoppingCartHandler.addProduct(id);
    }

    public void removeProduct(int id) {
        shoppingCartHandler.removeProduct(id);
    }
}

