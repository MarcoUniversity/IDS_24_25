package com.example.filiera_francoletti_belardinelli_raiola.model.Payment;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Prodotto> products = new ArrayList<>();

    public Carrello() {}

    public List<Prodotto> getProducts() { return products; }

    public void addProduct(Prodotto product) {
        if (product != null) {
            products.add(product);
        }
    }

    public void removeProduct(Long id) {
        products.removeIf(product -> product.getId().intValue() == id);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public void clearProducts() {this.products.clear(); }
}
