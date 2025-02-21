package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class HandlerCuratore {
    private List<Prodotto> productsToVerify;

    public HandlerCuratore(List<Prodotto> productsToVerify) {
        this.productsToVerify = productsToVerify;
    }

    public List<Prodotto> getProductsToVerify() {
        return productsToVerify;
    }

    public void setProductsToVerify(List<Prodotto> productsToVerify) {
        this.productsToVerify = productsToVerify;
    }

    public Prodotto getProductById(int id) {
        for (Prodotto product : productsToVerify) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void verifyProduct(int id) {
        Prodotto product = getProductById(id);
        if (product != null) {
            product.setState(true);
            productsToVerify.remove(product);
        }
    }

    public void upload(Prodotto product) {
        productsToVerify.add(product);
    }
}

