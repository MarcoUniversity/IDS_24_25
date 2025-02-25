package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.repository.EventoRepository;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/map")
public class MappaController {

    private final ProdottoRepository prodottoRepository;
    private final EventoRepository eventoRepository;

    @Autowired
    public MappaController(ProdottoRepository prodottoRepository, EventoRepository eventoRepository) {
        this.prodottoRepository = prodottoRepository;
        this.eventoRepository = eventoRepository;
    }

    @GetMapping("/indirizzi")
    public ResponseEntity<Set<Indirizzo>> getAllIndirizzi() {
        Set<Indirizzo> indirizzi = new HashSet<>();
        List<Prodotto> prodotti = this.prodottoRepository.findAll();
        for (Prodotto p : prodotti) {
            if (p.getProcessingLocation() != null) {
                indirizzi.add(p.getProcessingLocation());
            }
        }
        List<Evento> eventi = this.eventoRepository.findAll();
        for (Evento e : eventi) {
            if (e.getPlace() != null) {
                indirizzi.add(e.getPlace());
            }
        }
        return ResponseEntity.ok(indirizzi);
    }
}
