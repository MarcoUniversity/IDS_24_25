package com.example.filiera_francoletti_belardinelli_raiola.Model;

public class Indirizzo {
    private String address;
    private int number;

    public Indirizzo(String address, int number) {
        this.address = address;
        this.number = number;
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
}
