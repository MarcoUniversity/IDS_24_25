package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerCuratore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.List;

public class Curatore {
    private String name;
    private HandlerCuratore curatorHandler;

    public Curatore(String name, HandlerCuratore curatorHandler) {
        this.name = name;
        this.curatorHandler = curatorHandler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void verify(int id) {
        curatorHandler.verifyProduct(id);
    }

    public void upload(Prodotto product) {
        curatorHandler.uploadProduct(product);
    }

    public List<Prodotto> getProductsList() {
        return curatorHandler.getProductsToVerify();
    }

    public Prodotto getProductById(int id) {
        return curatorHandler.getProductById(id);
    }
}

