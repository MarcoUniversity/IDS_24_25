package com.example.filiera_francoletti_belardinelli_raiola.model.Users;

import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerAcquirente;
import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Ricevuta;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Acquirente extends UtenteGenerico {
    @Transient
    private HandlerAcquirente buyerHandler;
    @Id
    private Long id;

    public Acquirente() {
        super();
        this.buyerHandler = new HandlerAcquirente();
    }
    public Acquirente(String name, String email, String password) {
        super(name, email, password);
        this.buyerHandler = new HandlerAcquirente();
    }

    // Getter e Setter per buyerHandler
    public HandlerAcquirente getBuyerHandler() {
        return buyerHandler;
    }

    public void setBuyerHandler(HandlerAcquirente buyerHandler) {
        this.buyerHandler = buyerHandler;
    }

    // Metodi di business delegati al buyerHandler
    public void addProduct(Long id) {
        buyerHandler.addProduct(id);
    }

    public Ricevuta pay() {
        return buyerHandler.pay(this).getInvoice();
    }

    public void removeProduct(Long id) {
        buyerHandler.removeProduct(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
