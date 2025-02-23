package com.example.filiera_francoletti_belardinelli_raiola.Model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_prodotto", discriminatorType = DiscriminatorType.STRING)
public class Prodotto {

    private String name;
    private double price;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;

    @Embedded
    private Indirizzo processingLocation;

    @ManyToOne
    private Venditore seller;

    private boolean state;

    public Prodotto(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.expiration = expiration;
        this.processingLocation = processingLocation;
        this.state = false;
        this.seller = seller;
    }

    public Prodotto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Indirizzo getProcessingLocation() {
        return processingLocation;
    }

    public void setProcessingLocation(Indirizzo processingLocation) {
        this.processingLocation = processingLocation;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Venditore getSeller() {
        return seller;
    }

    public void setSeller(Venditore seller) {
        this.seller = seller;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
