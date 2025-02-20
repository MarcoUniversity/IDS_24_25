package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Piattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class HandlerCarrello {
    private List<Prodotto> products;

    public HandlerCarrello(List<Prodotto> products) {
        this.products = products;
    }

    public List<Prodotto> getProducts() {
        return products;
    }

    public void setProducts(List<Prodotto> products) {
        this.products = products;
    }

    public void addProduct(int id) {
        Piattaforma pf=Piattaforma.getPlatform();
        products.add(pf.getProductByID(id));
    }

    public void removeProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    public Prodotto getProductById(int id) {
        for (Prodotto product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
