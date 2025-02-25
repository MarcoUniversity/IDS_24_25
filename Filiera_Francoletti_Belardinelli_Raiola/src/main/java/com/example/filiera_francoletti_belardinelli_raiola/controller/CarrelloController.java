package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Carrello;
import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Pagamento;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerAcquirente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST per la gestione del carrello degli acquirenti.
 * Fornisce API per aggiungere e rimuovere prodotti, visualizzare il carrello e effettuare pagamenti.
 */
@RestController
@RequestMapping("/api/v1/acquirenti")
public class CarrelloController {

    private final HandlerAcquirente handlerAcquirente;

    /**
     * Costruttore per l'iniezione delle dipendenze necessarie.
     *
     * @param handlerAcquirente servizio per la gestione del carrello e dei pagamenti
     */
    @Autowired
    public CarrelloController(HandlerAcquirente handlerAcquirente) {
        this.handlerAcquirente = handlerAcquirente;
    }

    /**
     * Aggiunge un prodotto al carrello dell'acquirente.
     *
     * @param id ID dell'acquirente
     * @param productId ID del prodotto da aggiungere
     * @return ResponseEntity con stato HTTP 200 OK
     */
    @PostMapping("/{id}/cart/add")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long id,
                                                 @RequestParam Long productId) {
        this.handlerAcquirente.addProduct(productId);
        return ResponseEntity.ok().build();
    }

    /**
     * Rimuove un prodotto dal carrello dell'acquirente.
     *
     * @param id ID dell'acquirente
     * @param productId ID del prodotto da rimuovere
     * @return ResponseEntity con stato HTTP 204 No Content
     */
    @DeleteMapping("/{id}/cart/remove")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long id,
                                                      @RequestParam Long productId) {
        this.handlerAcquirente.removeProduct(productId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Restituisce il carrello corrente dell'acquirente.
     *
     * @param id ID dell'acquirente
     * @return ResponseEntity contenente il carrello dell'acquirente
     */
    @GetMapping("/{id}/cart")
    public ResponseEntity<Carrello> getCart(@PathVariable Long id) {
        Carrello cart = this.handlerAcquirente.getShoppingCart();
        return ResponseEntity.ok(cart);
    }

    /**
     * Effettua il pagamento del carrello dell'acquirente svuotandolo e restituendo la ricevuta.
     *
     * @param id ID dell'acquirente
     * @return ResponseEntity contenente la ricevuta del pagamento o un messaggio di errore
     */
    @PostMapping("/{id}/cart/pay")
    public ResponseEntity<String> payCart(@PathVariable Long id) {
        Pagamento pagamento = this.handlerAcquirente.pay(null);
        if (pagamento == null || pagamento.getInvoice() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Pagamento fallito");
        }
        return ResponseEntity.ok(pagamento.getInvoice().getInvoice());
    }
}
