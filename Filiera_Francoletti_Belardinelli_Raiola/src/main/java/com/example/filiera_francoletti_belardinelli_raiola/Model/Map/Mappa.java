package com.example.filiera_francoletti_belardinelli_raiola.Model.Map;

import java.util.ArrayList;
import java.util.List;

public class Mappa {
    private List<Indirizzo> listOfAddresses;
    private static Mappa instanceMap;

    private Mappa() {
        // Costruttore privato per implementare il pattern Singleton
        listOfAddresses = new ArrayList<Indirizzo>();
    }

    public static synchronized Mappa getMap() {
        if (instanceMap == null) {
            instanceMap = new Mappa();
        }
        return instanceMap;
    }
    public void addIndirizzo(Indirizzo indirizzo) {
        if (indirizzo != null) {
            this.listOfAddresses.add(indirizzo);
        }
    }

    public void removeIndirizzo(Indirizzo indirizzo) {
        this.listOfAddresses.remove(indirizzo);
    }



    public List<Indirizzo> getListOfAddresses() {
        return this.listOfAddresses;
    }

    public void setListOfAddresses(List<Indirizzo> listOfAddresses) {
        this.listOfAddresses = listOfAddresses;
    }
}

