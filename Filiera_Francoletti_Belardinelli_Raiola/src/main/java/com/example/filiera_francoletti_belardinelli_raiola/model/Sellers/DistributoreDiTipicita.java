package com.example.filiera_francoletti_belardinelli_raiola.model.sellers;

import com.example.filiera_francoletti_belardinelli_raiola.model.map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.product.ProdottoDistributore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import java.util.Date;

/**
 * Classe che rappresenta un distributore di tipicità, ovvero un venditore specializzato nella distribuzione di prodotti tipici.
 * Estende la classe {@link Venditore} e implementa l'interfaccia {@link IBuilder} per la creazione di bundle di prodotti.
 */
@Entity
@DiscriminatorValue("DISTRIBUTORE")
public class DistributoreDiTipicita extends Venditore implements IBuilder {

    /**
     * Bundle temporaneo in costruzione.
     */
    @Transient
    private ProdottoDistributore currentBundle = null;

    /**
     * Costruttore di default richiesto da JPA.
     */
    public DistributoreDiTipicita() {
        super();
    }

    /**
     * Costruttore che inizializza un distributore con il nome e l'indirizzo specificati.
     *
     * @param name Nome del distributore.
     * @param address Indirizzo del distributore.
     */
    public DistributoreDiTipicita(String name, Indirizzo address) {
        super(name, address);
    }

    /**
     * Inizia la costruzione di un bundle di prodotti.
     *
     * @param bundleName Nome del bundle.
     * @param price Prezzo del bundle.
     * @param description Descrizione del bundle.
     * @param expiration Data di scadenza del bundle.
     */
    public void startBundle(String bundleName, double price, String description, Date expiration) {
        this.currentBundle = new ProdottoDistributore(bundleName, price, description, expiration, getAddress(), this, null);
    }

    /**
     * Aggiunge un sottoprodotto al bundle corrente.
     *
     * @param subProduct Prodotto da aggiungere al bundle.
     */
    public void addSubProduct(Prodotto subProduct) {
        if (this.currentBundle != null && subProduct != null) {
            this.currentBundle.addProdotto(subProduct);
        } else {
            System.out.println("Errore: nessun bundle in corso oppure subProduct nullo.");
        }
    }

    /**
     * Conclude la costruzione del bundle, lo restituisce e resetta il bundle corrente.
     *
     * @return Il bundle di prodotti completato.
     */
    public Prodotto finishBundle() {
        if (this.currentBundle == null) {
            System.out.println("Nessun bundle in corso.");
            return null;
        }
        Prodotto finishedBundle = this.currentBundle;
        this.currentBundle = null;
        return finishedBundle;
    }

    /**
     * Metodo factory per creare un prodotto generico per il distributore.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     * @return Un'istanza di {@link ProdottoDistributore} con i dettagli specificati.
     */
    @Override
    public Prodotto createProduct(String name, double price, String description, Date expiration) {
        return new ProdottoDistributore(name, price, description, expiration, getAddress(), this, null);
    }
}
