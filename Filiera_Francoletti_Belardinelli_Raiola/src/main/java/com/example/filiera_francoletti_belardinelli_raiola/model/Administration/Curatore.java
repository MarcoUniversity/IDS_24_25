package com.example.filiera_francoletti_belardinelli_raiola.model.Administration;
import jakarta.persistence.*;

/**
 * Classe che rappresenta un Curatore nel sistema.
 * Un curatore ha un identificativo univoco e un nome.
 */
@Entity
public class Curatore {

    /**
     * Identificativo univoco del curatore, generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome del curatore.
     */
    private String name;

    /**
     * Costruttore di default richiesto da JPA.
     */
    public Curatore() {
    }

    /**
     * Costruttore che inizializza un curatore con un nome specificato.
     *
     * @param name Il nome del curatore.
     */
    public Curatore(String name) {
        this.name = name;
    }

    /**
     * Restituisce l'identificativo del curatore.
     *
     * @return L'ID del curatore.
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'identificativo del curatore.
     *
     * @param id Il nuovo ID del curatore.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il nome del curatore.
     *
     * @return Il nome del curatore.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome del curatore.
     *
     * @param name Il nuovo nome del curatore.
     */
    public void setName(String name) {
        this.name = name;
    }
}
