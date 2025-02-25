package com.example.filiera_francoletti_belardinelli_raiola.model.social;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Social che gestisce una lista di contenuti social (advertisement) e implementa il design pattern Singleton.
 * <p>
 * Questa classe è annotata con {@code @Component} per essere gestita come bean da Spring.
 * </p>
 */
@Component
public class Social {

    /**
     * Lista dei contenuti social (advertisement) gestiti dalla piattaforma.
     */
    private List<ContenutoSocial> listOfSocialAdvertisement;

    /**
     * Istanza singleton della classe Social.
     */
    private static Social instanceSocial;

    /**
     * Costruttore privato per implementare il design pattern Singleton.
     * Inizializza la lista dei contenuti social.
     */
    private Social() {
        listOfSocialAdvertisement = new ArrayList<ContenutoSocial>();
    }

    /**
     * Restituisce l'istanza singleton della classe Social.
     * Il metodo è sincronizzato per garantire la sicurezza nel contesto di accesso concorrente.
     *
     * @return l'istanza di {@code Social}
     */
    public static synchronized Social getSocial() {
        if (instanceSocial == null) {
            instanceSocial = new Social();
        }
        return instanceSocial;
    }

    /**
     * Restituisce la lista dei contenuti social (advertisement) attualmente gestiti.
     *
     * @return la lista di {@code ContenutoSocial}
     */
    public List<ContenutoSocial> getListOfSocialAdvertisement() {
        return listOfSocialAdvertisement;
    }

    /**
     * Imposta la lista dei contenuti social .
     *
     * @param listOfSocialAdvertisement la lista di {@code ContenutoSocial} da impostare
     */
    public void setListOfSocialAdvertisement(List<ContenutoSocial> listOfSocialAdvertisement) {
        this.listOfSocialAdvertisement = listOfSocialAdvertisement;
    }

    /**
     * Aggiunge un nuovo contenuto social alla lista.
     *
     * @param social il {@code ContenutoSocial} da aggiungere
     */
    public void addSocialAdvertisement(ContenutoSocial social) {
        this.listOfSocialAdvertisement.add(social);
    }
}
