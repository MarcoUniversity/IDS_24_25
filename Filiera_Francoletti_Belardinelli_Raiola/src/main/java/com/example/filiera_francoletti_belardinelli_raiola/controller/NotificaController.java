package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.users.Notifica;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerNotifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifiche")
public class NotificaController {

    private final HandlerNotifica notificaService;

    @Autowired
    public NotificaController(HandlerNotifica notificaService) {
        this.notificaService = notificaService;
    }

    /**
     * POST: Crea manualmente una notifica.
     * URL di esempio:
     * http://localhost:8080/api/v1/notifiche/manual?utenteId=1&messaggio=Test%20Notifica
     */
    @PostMapping("/manual")
    public ResponseEntity<Notifica> createNotifica(@RequestParam Long utenteId,
                                                   @RequestParam String messaggio) {
        if (utenteId == null || messaggio == null || messaggio.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Notifica notifica = notificaService.creaNotifica(messaggio, utenteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(notifica);
    }

    /**
     * GET: Recupera tutte le notifiche per un utente specifico.
     * URL di esempio:
     * http://localhost:8080/api/v1/notifiche/utente/1
     */
    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<?> getNotificheByUtente(@PathVariable Long utenteId) {
        return ResponseEntity.ok(notificaService.getNotifichePerUtente(utenteId));
    }
}
