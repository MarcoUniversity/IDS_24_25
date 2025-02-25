package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Notifica;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerNotifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifiche")
public class NotificaController {

    private final HandlerNotifica notificaService;

    @Autowired
    public NotificaController(HandlerNotifica notificaService) {
        this.notificaService = notificaService;
    }

    // GET: Recupera tutte le notifiche per un utente specifico
    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<Notifica>> getNotificheByUtente(@PathVariable Long utenteId) {
        List<Notifica> notifiche = notificaService.getNotifichePerUtente(utenteId);
        return ResponseEntity.ok(notifiche);
    }

    // (Opzionale) POST: Crea una notifica manualmente (per test o per admin)
    @PostMapping
    public ResponseEntity<Notifica> createNotifica(@RequestParam Long utenteId,
                                                   @RequestParam String messaggio) {
        Notifica notifica = notificaService.creaNotifica(messaggio, utenteId);
        return ResponseEntity.status(201).body(notifica);
    }
}
