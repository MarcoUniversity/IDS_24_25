package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Administration.Piattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione delle informazioni sulla piattaforma, inclusi i prodotti e gli eventi.
 * Fornisce degli endpoint per accedere allo stato attuale della piattaforma.
 */
@RestController
@RequestMapping("/api/v1/platform")
public class PiattaformaController {

    /**
     * Recupera lo stato attuale della piattaforma, inclusi i prodotti e gli eventi.
     *
     * @return Un {@link ResponseEntity} che contiene i prodotti e gli eventi della piattaforma.
     */
    @GetMapping
    public ResponseEntity<?> getPlatformStatus() {
        Piattaforma platform = Piattaforma.getPlatform();
        return ResponseEntity.ok(new PlatformStatus(platform.getProductInPlatform(), platform.getEventInPlatform()));
    }

    /**
     * Classe che rappresenta lo stato della piattaforma con una lista di prodotti e eventi(DTO).
     */
    static class PlatformStatus {

        private List<Prodotto> products;
        private List<Evento> events;

        /**
         * Costruttore per il DTO PlatformStatus.
         *
         * @param products Una lista di prodotti presenti sulla piattaforma.
         * @param events Una lista di eventi presenti sulla piattaforma.
         */
        public PlatformStatus(List<Prodotto> products, List<Evento> events) {
            this.products = products;
            this.events = events;
        }

        /**
         * Ottiene la lista dei prodotti presenti sulla piattaforma.
         *
         * @return Una lista di oggetti {@link Prodotto}.
         */
        public List<Prodotto> getProducts() {
            return products;
        }

        /**
         * Ottiene la lista degli eventi presenti sulla piattaforma.
         *
         * @return Una lista di oggetti {@link Evento}.
         */
        public List<Evento> getEvents() {
            return events;
        }
    }
}
