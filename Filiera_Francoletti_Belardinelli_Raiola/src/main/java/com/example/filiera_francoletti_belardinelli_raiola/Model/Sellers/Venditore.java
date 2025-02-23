package com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerInvito;
import com.example.filiera_francoletti_belardinelli_raiola.Controller.HandlerVenditore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Events.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_venditore", discriminatorType = DiscriminatorType.STRING)
public abstract class Venditore implements IVenditore {

    private String name;

    @Embedded
    private Indirizzo address;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private HandlerVenditore handlerProduct;
    @Transient
    private HandlerInvito handlerInvite;

    public Venditore() {
    }

    public Venditore(String name, Indirizzo address) {
        this.name = name;
        this.address = address;
        this.handlerProduct = new HandlerVenditore(new ArrayList<>());
        this.handlerInvite =new HandlerInvito(new ArrayList<>());
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
    public final void loadProduct(String name, double price, String description, Date expiration) {
        Prodotto product = createProduct(name, price, description, expiration);
        this.handlerProduct.loadProduct(product);
    }

    protected abstract Prodotto createProduct(String name, double price, String description, Date expiration);


    public void manageInvite(int id){
        this.handlerInvite.manageInvite(id);
    }

    public void removeProduct(int id){
        this.handlerProduct.removeProduct(id);
    }

    public void socialPromotion(String description,int id){
        Prodotto product = this.getProductById(id);
        if (product != null) {
            this.handlerProduct.socialPromotion(description, product, this);
        }    }

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

    public Long getId(){
        return this.id;
    }

    public HandlerVenditore getHandlerProduct() {
        return handlerProduct;
    }
    public HandlerInvito getHandlerInvite() {
        return handlerInvite;
    }


}