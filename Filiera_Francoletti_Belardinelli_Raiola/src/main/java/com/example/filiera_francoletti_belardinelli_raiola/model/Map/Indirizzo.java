package com.example.filiera_francoletti_belardinelli_raiola.model.Map;

import jakarta.persistence.Embeddable;
import java.util.Objects;

/**
 * Classe che rappresenta un indirizzo.
 * Questa classe è utilizzata come un valore incorporato in altre entità.
 */
@Embeddable
public class Indirizzo {

    /**
     * Nome della via o dell'indirizzo.
     */
    private String address;

    /**
     * Numero civico associato all'indirizzo.
     */
    private int number;

    /**
     * Costruttore che inizializza un indirizzo con i valori specificati.
     *
     * @param address Nome della via.
     * @param number Numero civico.
     */
    public Indirizzo(String address, int number) {
        this.address = address;
        this.number = number;
    }

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Indirizzo() {}

    /**
     * Restituisce l'indirizzo.
     *
     * @return Nome della via.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Imposta l'indirizzo.
     *
     * @param address Nuovo nome della via.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Restituisce il numero civico.
     *
     * @return Numero civico.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Imposta il numero civico.
     *
     * @param number Nuovo numero civico.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Verifica se due indirizzi sono uguali confrontando l'indirizzo e il numero civico.
     *
     * @param o Oggetto da confrontare.
     * @return true se gli indirizzi sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Indirizzo that)) return false;
        return number == that.number && Objects.equals(this.address, that.address);
    }

    /**
     * Restituisce una rappresentazione testuale dell'indirizzo.
     *
     * @return Indirizzo in formato "via numero".
     */
    @Override
    public String toString() {
        return this.address + " " + this.number;
    }
}