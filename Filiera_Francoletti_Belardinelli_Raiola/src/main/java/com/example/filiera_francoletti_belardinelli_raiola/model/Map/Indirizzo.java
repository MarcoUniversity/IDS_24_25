package com.example.filiera_francoletti_belardinelli_raiola.model.Map;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Indirizzo {
    private String address;
    private int number;

    public Indirizzo(String address, int number) {
        this.address = address;
        this.number = number;
    }

    public Indirizzo() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Indirizzo that)) return false;
        return number == that.number && Objects.equals(this.address, that.address);
    }
    @Override
    public String toString() {
        return this.address + " " + this.number;
    }
}
