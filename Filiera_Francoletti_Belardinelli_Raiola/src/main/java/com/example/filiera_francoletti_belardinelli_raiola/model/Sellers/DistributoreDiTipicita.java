package com.example.filiera_francoletti_belardinelli_raiola.model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.ProdottoDistributore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import java.util.Date;

@Entity
@DiscriminatorValue("DISTRIBUTORE")
public class DistributoreDiTipicita extends Venditore implements IBuilder {

    // Questo campo viene usato per costruire un bundle temporaneo
    @Transient
    private ProdottoDistributore currentBundle = null;

    public DistributoreDiTipicita() {
        super();
    }

    public DistributoreDiTipicita(String name, Indirizzo address) {
        super(name, address);
    }

    /**
     * Inizia la costruzione di un bundle di prodotti.
     */
    public void startBundle(String bundleName, double price, String description, Date expiration) {
        this.currentBundle = new ProdottoDistributore(bundleName, price, description, expiration, getAddress(), this, null);
    }

    /**
     * Aggiunge un sottoprodotto al bundle corrente.
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
     */
    public Prodotto finishBundle() {
        if (this.currentBundle == null) {
            System.out.println("Nessun bundle in corso.");
            return null;
        }
        Prodotto finishedBundle = this.currentBundle;
        // Reset del bundle corrente
        this.currentBundle = null;
        // Qui puoi eventualmente invocare un metodo di salvataggio, ad esempio:
        // getHandlerProduct().loadProduct(finishedBundle);
        return finishedBundle;
    }

    /**
     * Metodo factory per creare un prodotto generico per il distributore.
     */
    @Override
    public Prodotto createProduct(String name, double price, String description, Date expiration) {
        return new ProdottoDistributore(name, price, description, expiration, getAddress(), this, null);
    }
}
