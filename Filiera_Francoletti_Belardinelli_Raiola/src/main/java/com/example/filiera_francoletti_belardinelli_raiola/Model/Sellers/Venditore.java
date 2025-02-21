package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerInvito;
import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerVenditore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Subscriber;

import java.util.Date;
import java.util.List;

public abstract class Venditore implements IVenditore, Subscriber {

    private String name;
    private Indirizzo address;
    private static int idSeller=0;
    private HandlerVenditore handlerProduct;
    private HandlerInvito handlerInvite;

    public Venditore(String name, Indirizzo address, HandlerVenditore handlerProduct, HandlerInvito handlerInvite) {
        this.name = name;
        this.address = address;
        this.idSeller ++;
        this.handlerProduct = handlerProduct;
        this.handlerInvite = handlerInvite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Indirizzo getAddress() {
        return address;
    }

    public void setAddress(Indirizzo address) {
        this.address = address;
    }

    @Override
    public void loadProduct(String name, double price, String description, Date expiration) {
        this.handlerProduct.loadProduct(new Prodotto(name,price,description,expiration,this.address,this));
    }

    public void manageInvite(int id){
        this.handlerInvite.manageInvite(id);
    }

    public void removeProduct(int id){
        this.handlerProduct.removeProduct(id);
    }

    public void socialPromotion(String description,int id){
        this.handlerProduct.socialPromotion(description,id,this);
    }

    public List<Prodotto> viewProducts (){
        return this.handlerProduct.getUploadedProducts();
    }

    public Prodotto getProductById(int id){
        return this.handlerProduct.getProductById(id);
    }

    public List<Invito> getInvite(){
        return this.handlerInvite.getInvite();
    }

    public Invito getInviteById(int id){
        return this.handlerInvite.getInviteById(id);
    }

    public int getId(){
        return this.idSeller;
    }

    @Override
    public void update() {

    }
}