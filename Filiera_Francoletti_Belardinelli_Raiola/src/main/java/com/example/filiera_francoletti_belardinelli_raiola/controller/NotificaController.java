package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Notifica;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerNotifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione delle notifiche degli utenti.
 */
@RestController
@RequestMapping("/api/v1/notifiche")
public class NotificaController {

    private final HandlerNotifica notificaService;

    /**
     * Costruttore del controller, che inietta il servizio di gestione delle notifiche.
     *
     * @param notificaService il servizio di gestione delle notifiche
     */
    @Autowired
    public NotificaController(HandlerNotifica notificaService) {
        this.notificaService = notificaService;
    }

    /**
     * Recupera tutte le notifiche per un utente specifico.
     *
     * @param utenteId ID dell'utente di cui si vogliono recuperare le notifiche
     * @return ResponseEntity contenente una lista di notifiche dell'utente
     */
    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<Notifica>> getNotificheByUtente(@PathVariable Long utenteId) {
        List<Notifica> notifiche = notificaService.getNotifichePerUtente(utenteId);
        return ResponseEntity.ok(notifiche);
    }

    /**
     * Crea una notifica per l'utente.
     *
     * @param utenteId  ID dell'utente a cui inviare la notifica
     * @param messaggio Contenuto della notifica
     * @return ResponseEntity contenente la notifica creata
     */
    @PostMapping
    public ResponseEntity<Notifica> createNotifica(@RequestParam Long utenteId,
                                                   @RequestParam String messaggio) {
        Notifica notifica = notificaService.creaNotifica(messaggio, utenteId);
        return ResponseEntity.status(201).body(notifica);
    }
}
