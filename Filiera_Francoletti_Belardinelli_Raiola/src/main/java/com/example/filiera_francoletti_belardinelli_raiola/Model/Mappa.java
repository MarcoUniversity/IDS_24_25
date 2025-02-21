package com.example.filiera_francoletti_belardinelli_raiola.Model;

import java.util.List;

public class Mappa {
    private List<Indirizzo> listOfAddresses;
    private static Mappa instance;

    private Mappa() {
        // Costruttore privato per implementare il pattern Singleton
    }

    public static Mappa getMap() {
        if (instance == null) {
            instance = new Mappa();
        }
        return instance;
    }

    public List<Indirizzo> getListOfAddresses() {
        return listOfAddresses;
    }

    public void setListOfAddresses(List<Indirizzo> listOfAddresses) {
        this.listOfAddresses = listOfAddresses;
    }
}

