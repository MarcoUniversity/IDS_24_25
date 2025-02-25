package com.example.filiera_francoletti_belardinelli_raiola.model.Sellers;

import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerInvito;
import com.example.filiera_francoletti_belardinelli_raiola.service.HandlerVenditore;
import com.example.filiera_francoletti_belardinelli_raiola.model.Map.Indirizzo;
import com.example.filiera_francoletti_belardinelli_raiola.model.Events.Invito;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Classe astratta che rappresenta un venditore nella filiera.
 * Questa classe fornisce una base comune per i vari tipi di venditori,
 * tra cui {@link Trasformatore}, {@link Produttore} e {@link DistributoreDiTipicita}.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Trasformatore.class, name = "trasformatore"),
        @JsonSubTypes.Type(value = Produttore.class, name = "produttore"),
        @JsonSubTypes.Type(value = DistributoreDiTipicita.class, name = "distributore")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_venditore", discriminatorType = DiscriminatorType.STRING)
public abstract class Venditore implements IVenditore {

    /**
     * Nome del venditore.
     */
    private String name;

    /**
     * Indirizzo del venditore.
     */
    @Embedded
    private Indirizzo address;

    /**
     * Identificativo univoco del venditore, generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Venditore() {}

    /**
     * Costruttore che inizializza un venditore con nome e indirizzo.
     *
     * @param name Nome del venditore.
     * @param address Indirizzo del venditore.
     */
    public Venditore(String name, Indirizzo address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Metodo astratto che deve essere implementato dalle sottoclassi per creare un prodotto specifico.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     * @return Il prodotto creato.
     */
    public abstract Prodotto createProduct(String name, double price, String description, Date expiration);

    /**
     * Carica un nuovo prodotto nel sistema.
     *
     * @param name Nome del prodotto.
     * @param price Prezzo del prodotto.
     * @param description Descrizione del prodotto.
     * @param expiration Data di scadenza del prodotto.
     */
    @Override
    public final void loadProduct(String name, double price, String description, Date expiration) {
        Prodotto product = createProduct(name, price, description, expiration);
        // La logica di salvataggio verrà gestita nel service appropriato.
    }

    /**
     * Restituisce il nome del venditore.
     *
     * @return Nome del venditore.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome del venditore.
     *
     * @param name Nuovo nome del venditore.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce l'indirizzo del venditore.
     *
     * @return Indirizzo del venditore.
     */
    public Indirizzo getAddress() {
        return address;
    }

    /**
     * Imposta l'indirizzo del venditore.
     *
     * @param address Nuovo indirizzo del venditore.
     */
    public void setAddress(Indirizzo address) {
        this.address = address;
    }

    /**
     * Restituisce l'identificativo univoco del venditore.
     *
     * @return ID del venditore.
     */
    public Long getId() {
        return this.id;
    }
}
