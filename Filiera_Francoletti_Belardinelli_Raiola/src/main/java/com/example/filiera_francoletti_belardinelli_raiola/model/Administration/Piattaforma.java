package com.example.filiera_francoletti_belardinelli_raiola.model.Administration;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Mappa;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe singleton che rappresenta la piattaforma di gestione dei prodotti ed eventi.
 * Contiene liste di prodotti ed eventi registrati e permette la loro gestione.
 */
@Component
public class Piattaforma {
    private List<Prodotto> productInPlatform;
    private List<Evento> eventInPlatform;
    private static Piattaforma instancePlatform;

    /**
     * Costruttore privato per implementare il pattern Singleton.
     */
    private Piattaforma() {
        productInPlatform = new ArrayList<>();
        eventInPlatform = new ArrayList<>();
    }

    /**
     * Restituisce l'istanza unica della piattaforma.
     *
     * @return L'istanza della piattaforma.
     */
    public static synchronized Piattaforma getPlatform() {
        if (instancePlatform == null) {
            instancePlatform = new Piattaforma();
        }
        return instancePlatform;
    }

    /**
     * Restituisce la lista dei prodotti presenti nella piattaforma.
     *
     * @return Lista dei prodotti.
     */
    public List<Prodotto> getProductInPlatform() {
        return productInPlatform;
    }

    /**
     * Imposta la lista dei prodotti presenti nella piattaforma.
     *
     * @param productInPlatform Lista di prodotti.
     */
    public void setProductInPlatform(List<Prodotto> productInPlatform) {
        this.productInPlatform = productInPlatform;
    }

    /**
     * Restituisce la lista degli eventi presenti nella piattaforma.
     *
     * @return Lista degli eventi.
     */
    public List<Evento> getEventInPlatform() {
        return eventInPlatform;
    }

    /**
     * Imposta la lista degli eventi presenti nella piattaforma.
     *
     * @param eventInPlatform Lista di eventi.
     */
    public void setEventInPlatform(List<Evento> eventInPlatform) {
        this.eventInPlatform = eventInPlatform;
    }

    /**
     * Aggiunge un prodotto alla piattaforma e aggiorna la mappa con la sua posizione.
     *
     * @param product Il prodotto da aggiungere.
     */
    public void addProductInPlatform(Prodotto product) {
        Mappa map = Mappa.getMap();
        map.addIndirizzo(product.getProcessingLocation());
        productInPlatform.add(product);
    }

    /**
     * Rimuove un prodotto dalla piattaforma in base al suo ID.
     *
     * @param id ID del prodotto da rimuovere.
     */
    public void removeProduct(int id) {
        productInPlatform.removeIf(product -> product.getId() == id);
    }

    /**
     * Aggiunge un evento alla piattaforma e aggiorna la mappa con la sua posizione.
     *
     * @param event L'evento da aggiungere.
     */
    public void addEventInPlatform(Evento event) {
        eventInPlatform.add(event);
        Mappa map = Mappa.getMap();
        map.addIndirizzo(event.getPlace());
    }

    /**
     * Rimuove un evento dalla piattaforma in base al suo ID.
     *
     * @param id ID dell'evento da rimuovere.
     */
    public void removeEvent(int id) {
        eventInPlatform.removeIf(event -> event.getId() == id);
    }

    /**
     * Restituisce un prodotto in base al suo ID.
     *
     * @param id ID del prodotto da recuperare.
     * @return Il prodotto corrispondente all'ID, oppure null se non trovato.
     */
    public Prodotto getProductByID(Long id) {
        for (Prodotto product : productInPlatform) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Restituisce un evento in base al suo ID.
     *
     * @param id ID dell'evento da recuperare.
     * @return L'evento corrispondente all'ID, oppure null se non trovato.
     */
    public Evento getEventByID(Long id) {
        for (Evento event : eventInPlatform) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }
}
