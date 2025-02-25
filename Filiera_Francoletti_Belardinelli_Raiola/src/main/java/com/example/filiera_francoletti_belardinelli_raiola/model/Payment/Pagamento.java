package com.example.filiera_francoletti_belardinelli_raiola.model.Payment;

import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Acquirente;
import jakarta.persistence.*;

/**
 * Classe che rappresenta un pagamento effettuato da un acquirente.
 * Contiene informazioni sull'acquirente, il carrello con i prodotti acquistati e la ricevuta del pagamento.
 */
@Entity
public class Pagamento {

    /**
     * Identificativo univoco del pagamento, generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Acquirente che effettua il pagamento.
     */
    @ManyToOne
    private Acquirente payer;

    /**
     * Carrello contenente i prodotti acquistati.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Carrello products;

    /**
     * Ricevuta del pagamento.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Ricevuta invoice;

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Pagamento() {}

    /**
     * Costruttore che inizializza un pagamento con le informazioni fornite.
     *
     * @param payer Acquirente che effettua il pagamento.
     * @param products Carrello contenente i prodotti acquistati.
     * @param invoice Ricevuta associata al pagamento.
     */
    public Pagamento(Acquirente payer, Carrello products, Ricevuta invoice) {
        this.payer = payer;
        this.products = products;
        this.invoice = invoice;
    }

    /**
     * Restituisce l'ID del pagamento.
     *
     * @return L'ID del pagamento.
     */
    public Long getId() { return id; }

    /**
     * Imposta l'ID del pagamento.
     *
     * @param id Nuovo ID del pagamento.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Restituisce l'acquirente che ha effettuato il pagamento.
     *
     * @return L'acquirente che ha effettuato il pagamento.
     */
    public Acquirente getPayer() { return payer; }

    /**
     * Imposta l'acquirente che ha effettuato il pagamento.
     *
     * @param payer Nuovo acquirente del pagamento.
     */
    public void setPayer(Acquirente payer) { this.payer = payer; }

    /**
     * Restituisce il carrello contenente i prodotti acquistati.
     *
     * @return Il carrello dei prodotti acquistati.
     */
    public Carrello getProducts() { return products; }

    /**
     * Imposta il carrello contenente i prodotti acquistati.
     *
     * @param products Nuovo carrello associato al pagamento.
     */
    public void setProducts(Carrello products) { this.products = products; }

    /**
     * Restituisce la ricevuta del pagamento.
     *
     * @return La ricevuta del pagamento.
     */
    public Ricevuta getInvoice() { return invoice; }

    /**
     * Imposta la ricevuta del pagamento.
     *
     * @param invoice Nuova ricevuta associata al pagamento.
     */
    public void setInvoice(Ricevuta invoice) { this.invoice = invoice; }
}
