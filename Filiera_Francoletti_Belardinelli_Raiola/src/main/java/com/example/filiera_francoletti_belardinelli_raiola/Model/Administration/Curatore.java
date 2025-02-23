package com.example.filiera_francoletti_belardinelli_raiola.Model.Administration;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerCuratore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Curatore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Transient
    private HandlerCuratore curatorHandler;

    public Curatore() {
        this.curatorHandler = new HandlerCuratore(new ArrayList<>());
    }

    public Curatore(String name) {
        this.name = name;
        this.curatorHandler = new HandlerCuratore(new ArrayList<>());
    }

    public void addProductToVerify(Prodotto product) {
        curatorHandler.getProductsToVerify().add(product);
    }

    public void verify(int id) {
        curatorHandler.verifyProduct(id);
        curatorHandler.upload(getProductById(id));
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public HandlerCuratore getCuratorHandler() { return curatorHandler; }
    public void setCuratorHandler(HandlerCuratore curatorHandler) { this.curatorHandler = curatorHandler; }
}
