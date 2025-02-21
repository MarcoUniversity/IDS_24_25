package com.example.filiera_francoletti_belardinelli_raiola.Model;

import java.util.List;

public class Social {
    private List<ContenutoSocial> listOfSocialAdvertisement;

    public Social(List<ContenutoSocial> listOfSocialAdvertisement) {
        this.listOfSocialAdvertisement = listOfSocialAdvertisement;
    }

    public List<ContenutoSocial> getListOfSocialAdvertisement() {
        return listOfSocialAdvertisement;
    }

    public void setListOfSocialAdvertisement(List<ContenutoSocial> listOfSocialAdvertisement) {
        this.listOfSocialAdvertisement = listOfSocialAdvertisement;
    }
}

