package com.example.filiera_francoletti_belardinelli_raiola.model.Payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Classe che rappresenta una ricevuta di pagamento.
 * Contiene un identificativo univoco e i dettagli della ricevuta.
 */
@Entity
public class Ricevuta {

    /**
     * Identificativo univoco della ricevuta, generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Dettagli della ricevuta in formato stringa.
     */
    private String invoice;

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Ricevuta() {}

    /**
     * Costruttore che inizializza una ricevuta con i dettagli forniti.
     *
     * @param invoice Dettagli della ricevuta.
     */
    public Ricevuta(String invoice) {
        this.invoice = invoice;
    }

    /**
     * Restituisce l'ID della ricevuta.
     *
     * @return L'ID univoco della ricevuta.
     */
    public Long getId() { return id; }

    /**
     * Imposta l'ID della ricevuta.
     *
     * @param id Nuovo ID della ricevuta.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Restituisce i dettagli della ricevuta.
     *
     * @return I dettagli della ricevuta.
     */
    public String getInvoice() { return invoice; }

    /**
     * Imposta i dettagli della ricevuta.
     *
     * @param invoice Nuovi dettagli della ricevuta.
     */
    public void setInvoice(String invoice) { this.invoice = invoice; }
}
