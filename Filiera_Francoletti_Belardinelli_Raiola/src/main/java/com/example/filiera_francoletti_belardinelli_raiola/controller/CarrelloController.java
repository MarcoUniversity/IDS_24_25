package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Pagamento;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerAcquirente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/acquirenti")
public class CarrelloController {

    private final HandlerAcquirente handlerAcquirente;

    @Autowired
    public CarrelloController(HandlerAcquirente handlerAcquirente) {
        this.handlerAcquirente = handlerAcquirente;
    }

    @PostMapping("/{id}/cart/add")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long id,
                                                 @RequestParam Long productId) {
        // In una implementazione reale dovresti recuperare l'acquirente in base a id,
        // poi chiamare handlerAcquirente.addProduct(productId) per aggiungere il prodotto al carrello dell'acquirente.
        handlerAcquirente.addProduct(productId);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}/cart/remove")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long id,
                                                      @RequestParam Long productId) {
        handlerAcquirente.removeProduct(productId);
        return ResponseEntity.noContent().build();
    }

    // Paga il carrello: svuota il carrello e restituisce una ricevuta
    // Esempio: POST http://localhost:8080/api/v1/acquirenti/1/cart/pay
    @PostMapping("/{id}/cart/pay")
    public ResponseEntity<String> payCart(@PathVariable Long id) {
        // In una soluzione reale, dovresti recuperare l'acquirente in base all'id (ad es. tramite un AcquirenteRepository).
        // Qui, per semplicità, passiamo null.
        Pagamento pagamento = handlerAcquirente.pay(null);
        if (pagamento == null || pagamento.getInvoice() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Pagamento fallito");
        }
        // Restituisce la ricevuta come stringa
        return ResponseEntity.ok(pagamento.getInvoice().getInvoice());
    }
}

