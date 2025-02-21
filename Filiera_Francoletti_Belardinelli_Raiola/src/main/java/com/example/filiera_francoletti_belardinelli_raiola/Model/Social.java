package com.example.filiera_francoletti_belardinelli_raiola.Model;

import java.util.List;

public class Social {
    private List<ContenutoSocial> listOfSocialAdvertisement;
    private static Social instanceSocial;


    private Social() {
        //Costruttore privato per implementare il design pattern singleton
    }

    public static Social getSocial() {
        if (instanceSocial == null) {
            instanceSocial = new Social();
        }
        return instanceSocial;
    }
    public List<ContenutoSocial> getListOfSocialAdvertisement() {
        return listOfSocialAdvertisement;
    }

    public void setListOfSocialAdvertisement(List<ContenutoSocial> listOfSocialAdvertisement) {
        this.listOfSocialAdvertisement = listOfSocialAdvertisement;
    }

    public void addSocialAdvertisement(ContenutoSocial social) {
        this.listOfSocialAdvertisement.add(social);
    }
}

