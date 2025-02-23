package com.example.filiera_francoletti_belardinelli_raiola.Controller;

import com.example.filiera_francoletti_belardinelli_raiola.Model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Map.Mappa;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Administration.Piattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.Model.Product.Prodotto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandlerUtente {

    public HandlerUtente() {}

    public List<Prodotto> viewProducts() {

        Piattaforma pf = Piattaforma.getPlatform();
        return pf.getProductInPlatform();
    }

    public List<Evento> viewEvents() {
        Piattaforma pf = Piattaforma.getPlatform();
        return pf.getEventInPlatform();
    }

    public Mappa viewMap() {
        Mappa map=Mappa.getMap();
        return map;
    }

    public Indirizzo traceProduct(Long id) {
        Piattaforma pf = Piattaforma.getPlatform();
        Prodotto prod = pf.getProductByID(id);
        if (prod != null) {
            return prod.getProcessingLocation();
        }
        return null;
    }

}

