package com.example.filiera_francoletti_belardinelli_raiola.model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerInvito;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerVenditore;
import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
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

    public Venditore() {
    }

    public Venditore(String name, Indirizzo address) {
        this.name = name;
        this.address = address;
    }

    // Metodo astratto: ogni sottoclasse implementerà come creare il prodotto specifico
    public abstract Prodotto createProduct(String name, double price, String description, Date expiration);

    // Metodo di utilità: il service (non l’entità) si occuperà di chiamarlo
    @Override
    public final void loadProduct(String name, double price, String description, Date expiration) {
        Prodotto product = createProduct(name, price, description, expiration);
        // La logica per il salvataggio del prodotto sarà gestita nel service
        // (per esempio, nel VenditoreService)
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

   /* public void manageInvite(int id){
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
    }*/

    public Long getId(){
        return this.id;
    }

   /* public HandlerVenditore getHandlerProduct() {
        return handlerProduct;
    }
    public HandlerInvito getHandlerInvite() {
        return handlerInvite;
    }*/


}