package com.example.filiera_francoletti_belardinelli_raiola.Model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@DiscriminatorValue("DISTRIBUTORE")
public class ProdottoDistributore extends Prodotto{

    @OneToMany(cascade = CascadeType.ALL)
    private List<Prodotto>listOfProduct;

    public ProdottoDistributore(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller,List<Prodotto>listOfProduct) {
        super(name, price, description, expiration, processingLocation, seller);
        if (listOfProduct == null) {
            this.listOfProduct = new ArrayList<>();
        } else {
            this.listOfProduct = listOfProduct;
        }
    }

    public ProdottoDistributore() {

    }

    public void addProdotto(Prodotto prodotto) {
        if (prodotto != null) {
            this.listOfProduct.add(prodotto);
        }
    }
    public List<Prodotto> getListOfProduct() {
        return listOfProduct;
    }
    public void setListOfProduct(List<Prodotto> listOfProduct) {
        this.listOfProduct = listOfProduct;
    }
}
