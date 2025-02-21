package com.example.filiera_francoletti_belardinelli_raiola.Model;

import java.util.List;

public class Mappa {
    private List<Indirizzo> listOfAddresses;
    private static Mappa instanceMap;

    private Mappa() {
        // Costruttore privato per implementare il pattern Singleton
    }

    public static Mappa getMap() {
        if (instanceMap == null) {
            instanceMap = new Mappa();
        }
        return instanceMap;
    }

    public List<Indirizzo> getListOfAddresses() {
        return listOfAddresses;
    }

    public void setListOfAddresses(List<Indirizzo> listOfAddresses) {
        this.listOfAddresses = listOfAddresses;
    }
}

