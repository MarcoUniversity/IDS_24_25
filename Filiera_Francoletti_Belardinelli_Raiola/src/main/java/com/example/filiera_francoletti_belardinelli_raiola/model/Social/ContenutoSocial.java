package com.example.filiera_francoletti_belardinelli_raiola.model.Social;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import jakarta.persistence.*;

@Entity
public class ContenutoSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Prodotto product;

    @ManyToOne
    private Venditore seller;

    private String description;

    public ContenutoSocial() {}

    public ContenutoSocial(Prodotto product, Venditore seller, String description) {
        this.product = product;
        this.seller = seller;
        this.description = description;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Prodotto getProduct() { return product; }
    public void setProduct(Prodotto product) { this.product = product; }
    public Venditore getSeller() { return seller; }
    public void setSeller(Venditore seller) { this.seller = seller; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
