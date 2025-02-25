package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Carrello;
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

    // Aggiunge un prodotto al carrello
    @PostMapping("/{id}/cart/add")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long id,
                                                 @RequestParam Long productId) {
        // In una implementazione reale, recuperi l'acquirente dal repository in base a "id"
        // Qui semplifichiamo e invociamo direttamente il metodo del service.
        handlerAcquirente.addProduct(productId);
        return ResponseEntity.ok().build();
    }

    // Rimuove un prodotto dal carrello
    @DeleteMapping("/{id}/cart/remove")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long id,
                                                      @RequestParam Long productId) {
        handlerAcquirente.removeProduct(productId);
        return ResponseEntity.noContent().build();
    }

    // Restituisce il carrello corrente dell'acquirente
    @GetMapping("/{id}/cart")
    public ResponseEntity<Carrello> getCart(@PathVariable Long id) {
        // In una implementazione reale, dovresti recuperare l'acquirente in base a "id"
        Carrello cart = handlerAcquirente.getShoppingCart();
        return ResponseEntity.ok(cart);
    }

    // Paga il carrello: svuota il carrello e restituisce la ricevuta
    @PostMapping("/{id}/cart/pay")
    public ResponseEntity<String> payCart(@PathVariable Long id) {
        // In una soluzione reale, recupera l'acquirente dal repository.
        // Qui, per semplicità, passiamo null al metodo pay.
        Pagamento pagamento = handlerAcquirente.pay(null);
        if (pagamento == null || pagamento.getInvoice() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Pagamento fallito");
        }
        return ResponseEntity.ok(pagamento.getInvoice().getInvoice());
    }
}
