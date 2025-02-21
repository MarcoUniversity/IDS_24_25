package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.ContenutoSocial;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Sellers.Venditore;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Social;

import java.util.List;

public class HandlerVenditore {
    private List<Prodotto> uploadedProducts;

    public HandlerVenditore(List<Prodotto> uploadedProducts) {
        this.uploadedProducts = uploadedProducts;
    }

    public List<Prodotto> getUploadedProducts() {
        return uploadedProducts;
    }

    public void setUploadedProducts(List<Prodotto> listOfProduct) {
        this.uploadedProducts = listOfProduct;
    }

    public void loadProduct(Prodotto product) {
        uploadedProducts.add(product);
    }

    public void removeProduct(int id) {
        uploadedProducts.removeIf(product -> product.getId() == id);
    }

    public Prodotto getProductById(int id) {
        for (Prodotto product : uploadedProducts) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean isProductVerified(int id) {
        Prodotto product = getProductById(id);
        return product != null && product.isState();
    }

    public void socialPromotion(String description, Prodotto product, Venditore seller) {
        Social social=Social.getSocial();
        social.addSocialAdvertisement(new ContenutoSocial(product,seller,description));
    }
}

