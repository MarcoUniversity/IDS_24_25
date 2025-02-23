package com.example.filiera_francoletti_belardinelli_raiola.Model.Payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Ricevuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoice;

    public Ricevuta() {}

    public Ricevuta(String invoice) {
        this.invoice = invoice;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInvoice() { return invoice; }
    public void setInvoice(String invoice) { this.invoice = invoice; }
}
