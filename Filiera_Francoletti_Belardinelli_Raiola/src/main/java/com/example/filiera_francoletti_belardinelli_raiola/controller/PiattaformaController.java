package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Administration.Piattaforma;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/platform")
public class PiattaformaController {

    // Piattaforma è gestita come componente singleton
    @GetMapping
    public ResponseEntity<?> getPlatformStatus() {
        Piattaforma platform = Piattaforma.getPlatform();
        // Puoi restituire un oggetto con prodotti ed eventi
        return ResponseEntity.ok(new PlatformStatus(platform.getProductInPlatform(), platform.getEventInPlatform()));
    }

    // DTO interno per restituire lo stato della piattaforma
    static class PlatformStatus {
        private List<Prodotto> products;
        private List<Evento> events;

        public PlatformStatus(List<Prodotto> products, List<Evento> events) {
            this.products = products;
            this.events = events;
        }

        public List<Prodotto> getProducts() {
            return products;
        }

        public List<Evento> getEvents() {
            return events;
        }
    }
}
