package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.events.Evento;
import com.example.filiera_francoletti_belardinelli_raiola.model.map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.product.Prodotto;
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

/**
 * Controller per la gestione della mappa, che fornisce gli indirizzi
 * relativi ai prodotti e agli eventi registrati nel sistema.
 */
@RestController
@RequestMapping("/api/v1/map")
public class MappaController {

    private final ProdottoRepository prodottoRepository;
    private final EventoRepository eventoRepository;

    /**
     * Costruttore del controller, che inietta i repository necessari.
     *
     * @param prodottoRepository il repository dei prodotti
     * @param eventoRepository   il repository degli eventi
     */
    @Autowired
    public MappaController(ProdottoRepository prodottoRepository, EventoRepository eventoRepository) {
        this.prodottoRepository = prodottoRepository;
        this.eventoRepository = eventoRepository;
    }

    /**
     * Recupera tutti gli indirizzi relativi ai prodotti e agli eventi.
     * Gli indirizzi dei prodotti provengono dalla loro sede di lavorazione,
     * mentre quelli degli eventi corrispondono al luogo dell'evento.
     *
     * @return ResponseEntity contenente un Set di indirizzi unici
     */
    @GetMapping("/indirizzi")
    public ResponseEntity<Set<Indirizzo>> getAllIndirizzi() {
        Set<Indirizzo> indirizzi = new HashSet<>();
        //recupera e aggiunge gli indirizzi dei prodotti
        List<Prodotto> prodotti = this.prodottoRepository.findAll();
        for (Prodotto p : prodotti) {
            if (p.getProcessingLocation() != null) {
                indirizzi.add(p.getProcessingLocation());
            }
        }
        //recupera e aggiunge gli indirizzi degli eventi
        List<Evento> eventi = this.eventoRepository.findAll();
        for (Evento e : eventi) {
            if (e.getPlace() != null) {
                indirizzi.add(e.getPlace());
            }
        }
        return ResponseEntity.ok(indirizzi);
    }
}
