package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Carrello;
import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Pagamento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Ricevuta;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Acquirente;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service per la gestione dell'acquirente e delle operazioni relative al carrello e ai pagamenti.
 */
@Service
public class HandlerAcquirente {

    private Carrello shoppingCart;
    private List<Pagamento> payments;
    private final ProdottoRepository prodottoRepository;

    /**
     * Costruttore per inizializzare il repository dei prodotti e il carrello.
     *
     * @param prodottoRepository repository per accedere ai prodotti disponibili.
     */
    @Autowired
    public HandlerAcquirente(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
        this.shoppingCart = new Carrello();
        this.payments = new ArrayList<>();
    }

    /**
     * Aggiunge un prodotto al carrello dell'acquirente.
     *
     * @param productId ID del prodotto da aggiungere.
     * @throws RuntimeException se il prodotto con l'ID specificato non esiste.
     */
    public void addProduct(Long productId) {
        Prodotto product = prodottoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + productId));
        this.shoppingCart.addProduct(product);
    }

    /**
     * Rimuove un prodotto dal carrello dell'acquirente.
     *
     * @param productId ID del prodotto da rimuovere.
     */
    public void removeProduct(Long productId) {
        this.shoppingCart.removeProduct(productId);
    }

    /**
     * Esegue il pagamento del carrello, genera una ricevuta, salva il pagamento e svuota il carrello.
     *
     * @param acquirente L'acquirente che effettua il pagamento.
     * @return L'oggetto Pagamento generato.
     */
    public Pagamento pay(Acquirente acquirente) {
        String invoiceContent = generateInvoice(this.shoppingCart);
        Ricevuta invoice = new Ricevuta(invoiceContent);
        Pagamento pagamento = new Pagamento(acquirente, this.shoppingCart, invoice);
        this.payments.add(pagamento);
        this.shoppingCart.clearProducts();
        return pagamento;
    }

    /**
     * Genera il contenuto della ricevuta basandosi sui prodotti presenti nel carrello.
     *
     * @param cart Il carrello dell'acquirente.
     * @return Il contenuto testuale della ricevuta.
     */
    private String generateInvoice(Carrello cart) {
        double total = 0;
        StringBuilder sb = new StringBuilder("Ricevuta:\n");
        for (Prodotto p : cart.getProducts()) {
            sb.append("Prodotto: ").append(p.getName())
                    .append(" - Prezzo: ").append(p.getPrice()).append("\n");
            total += p.getPrice();
        }
        sb.append("Totale: ").append(total);
        return sb.toString();
    }

    /**
     * Restituisce il carrello dell'acquirente.
     *
     * @return L'oggetto Carrello contenente i prodotti selezionati.
     */
    public Carrello getShoppingCart() {
        return shoppingCart;
    }

    /**
     * Restituisce la lista dei pagamenti effettuati.
     *
     * @return Lista di oggetti Pagamento.
     */
    public List<Pagamento> getPayments() {
        return payments;
    }
}
