package com.example.filiera_francoletti_belardinelli_raiola.Model;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerCuratore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;

import java.util.ArrayList;
import java.util.List;

public class Curatore {
    private String name;
    private HandlerCuratore curatorHandler;
    private static Curatore instance;

    private Curatore(String name) {
        this.name = name;
        this.curatorHandler =  new HandlerCuratore(new ArrayList<>());
    }

    public static synchronized  Curatore getCuratore() {
        if (instance == null) {
            instance = new Curatore("Curatore");
        }
        return instance;
    }

    public void addProductToVerify(Prodotto product) {
        this.curatorHandler.getProductsToVerify().add(product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void verify(int id) {
        curatorHandler.verifyProduct(id);
        curatorHandler.upload(this.getProductById(id));
    }

    private void upload(Prodotto product) {
        curatorHandler.upload(product);
    }

    public List<Prodotto> getProductsList() {
        return curatorHandler.getProductsToVerify();
    }

    public Prodotto getProductById(int id) {
        return curatorHandler.getProductById(id);
    }

    public HandlerCuratore getCuratorHandler() {
        return curatorHandler;
    }
}

