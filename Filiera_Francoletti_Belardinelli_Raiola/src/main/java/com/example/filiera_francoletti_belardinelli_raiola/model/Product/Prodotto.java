package com.example.filiera_francoletti_belardinelli_raiola.model.Product;

import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Sellers.Venditore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Classe che rappresenta un prodotto generico all'interno della filiera.
 * Questa classe utilizza l'ereditarietà per gestire diversi tipi di prodotti.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProdottoProduttore.class, name = "produttore"),
        @JsonSubTypes.Type(value = ProdottoTrasformatore.class, name = "trasformatore"),
        @JsonSubTypes.Type(value = ProdottoDistributore.class, name = "distributore")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_prodotto", discriminatorType = DiscriminatorType.STRING)
public class Prodotto {

    /**
     * Nome del prodotto.
     */
    private String name;

    /**
     * Prezzo del prodotto.
     */
    private double price;

    /**
     * Descrizione del prodotto.
     */
    private String description;

    /**
     * Identificativo univoco del prodotto, generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Data di scadenza del prodotto.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;

    /**
     * Indirizzo della località in cui il prodotto è stato lavorato.
     */
    @Embedded
    private Indirizzo processingLocation;

    /**
     * Venditore associato al prodotto.
     */
    @ManyToOne
    private Venditore seller;

    /**
     * Stato del prodotto (es. disponibile o non disponibile).
     */
    private boolean state;

    /**
     * Costruttore che inizializza un prodotto con le informazioni specificate.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     * @param processingLocation Indirizzo della lavorazione del prodotto.
     * @param seller Venditore del prodotto.
     */
    public Prodotto(String name, double price, String description, Date expiration, Indirizzo processingLocation, Venditore seller) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.expiration = expiration;
        this.processingLocation = processingLocation;
        this.state = false;
        this.seller = seller;
    }

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Prodotto() {}

    /**
     * Restituisce il nome del prodotto.
     *
     * @return Nome del prodotto.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome del prodotto.
     *
     * @param name Nuovo nome del prodotto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce il prezzo del prodotto.
     *
     * @return Prezzo del prodotto.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Imposta il prezzo del prodotto.
     *
     * @param price Nuovo prezzo del prodotto.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Restituisce la descrizione del prodotto.
     *
     * @return Descrizione del prodotto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione del prodotto.
     *
     * @param description Nuova descrizione del prodotto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Restituisce la data di scadenza del prodotto.
     *
     * @return Data di scadenza del prodotto.
     */
    public Date getExpiration() {
        return expiration;
    }

    /**
     * Imposta la data di scadenza del prodotto.
     *
     * @param expiration Nuova data di scadenza del prodotto.
     */
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    /**
     * Restituisce l'indirizzo della lavorazione del prodotto.
     *
     * @return Indirizzo della lavorazione del prodotto.
     */
    public Indirizzo getProcessingLocation() {
        return processingLocation;
    }

    /**
     * Imposta l'indirizzo della lavorazione del prodotto.
     *
     * @param processingLocation Nuovo indirizzo della lavorazione del prodotto.
     */
    public void setProcessingLocation(Indirizzo processingLocation) {
        this.processingLocation = processingLocation;
    }

    /**
     * Restituisce lo stato del prodotto.
     *
     * @return Stato del prodotto.
     */
    public boolean isState() {
        return state;
    }

    /**
     * Imposta lo stato del prodotto.
     *
     * @param state Nuovo stato del prodotto.
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * Restituisce il venditore del prodotto.
     *
     * @return Venditore del prodotto.
     */
    public Venditore getSeller() {
        return seller;
    }

    /**
     * Imposta il venditore del prodotto.
     *
     * @param seller Nuovo venditore del prodotto.
     */
    public void setSeller(Venditore seller) {
        this.seller = seller;
    }

    /**
     * Restituisce l'ID del prodotto.
     *
     * @return ID univoco del prodotto.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Imposta l'ID del prodotto.
     *
     * @param id Nuovo ID del prodotto.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
