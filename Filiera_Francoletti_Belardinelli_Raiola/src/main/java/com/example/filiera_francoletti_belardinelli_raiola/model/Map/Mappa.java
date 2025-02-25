package com.example.filiera_francoletti_belardinelli_raiola.model.map;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe singleton che rappresenta una mappa contenente una lista di indirizzi.
 * Permette di aggiungere e rimuovere indirizzi dalla mappa.
 */
@Component
public class Mappa {

    /**
     * Lista degli indirizzi presenti nella mappa.
     */
    private List<Indirizzo> listOfAddresses;

    /**
     * Istanza unica della mappa per l'implementazione del pattern Singleton.
     */
    private static Mappa instanceMap;

    /**
     * Costruttore privato per implementare il pattern Singleton.
     * Inizializza la lista degli indirizzi.
     */
    private Mappa() {
        listOfAddresses = new ArrayList<>();
    }

    /**
     * Restituisce l'istanza unica della mappa.
     *
     * @return L'istanza della mappa.
     */
    public static synchronized Mappa getMap() {
        if (instanceMap == null) {
            instanceMap = new Mappa();
        }
        return instanceMap;
    }

    /**
     * Aggiunge un indirizzo alla lista se non nullo.
     *
     * @param indirizzo L'indirizzo da aggiungere.
     */
    public void addIndirizzo(Indirizzo indirizzo) {
        if (indirizzo != null) {
            this.listOfAddresses.add(indirizzo);
        }
    }

    /**
     * Rimuove un indirizzo dalla lista se presente.
     *
     * @param indirizzo L'indirizzo da rimuovere.
     */
    public void removeIndirizzo(Indirizzo indirizzo) {
        this.listOfAddresses.remove(indirizzo);
    }

    /**
     * Restituisce la lista degli indirizzi presenti nella mappa.
     *
     * @return Lista di indirizzi.
     */
    public List<Indirizzo> getListOfAddresses() {
        return this.listOfAddresses;
    }

    /**
     * Imposta una nuova lista di indirizzi.
     *
     * @param listOfAddresses Nuova lista di indirizzi.
     */
    public void setListOfAddresses(List<Indirizzo> listOfAddresses) {
        this.listOfAddresses = listOfAddresses;
    }
}
