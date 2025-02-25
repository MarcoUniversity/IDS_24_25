package com.example.filiera_francoletti_belardinelli_raiola.model.Users;

import jakarta.persistence.*;

/**
 * Rappresenta un utente generico nel sistema.
 * <p>
 * Questa classe implementa l'interfaccia {@link Subscriber} ed è la classe base per altri tipi di utenti.
 * Utilizza l'ereditarietà SINGLE_TABLE con discriminatore per differenziare i vari tipi di utenti.
 * </p>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_utente", discriminatorType = DiscriminatorType.STRING)
public class UtenteGenerico implements Subscriber {

    /**
     * Identificatore univoco dell'utente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome dell'utente.
     */
    private String name;

    /**
     * Email dell'utente.
     */
    private String email;

    /**
     * Password dell'utente.
     */
    private String password;

    /**
     * Costruttore di default richiesto da JPA.
     */
    public UtenteGenerico() {}

    /**
     * Costruttore che inizializza un utente generico con i dati specificati.
     *
     * @param name     il nome dell'utente
     * @param email    l'indirizzo email dell'utente
     * @param password la password dell'utente
     */
    public UtenteGenerico(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Metodo di aggiornamento chiamato quando l'utente riceve una notifica.
     * Esegue l'azione di notifica, ad esempio stampando un messaggio su console.
     */
    @Override
    public void update() {
        System.out.println("[" + this.name + "] Ha ricevuto una notifica: nuovo evento creato!");
    }

    /**
     * Restituisce l'ID dell'utente.
     *
     * @return l'ID dell'utente
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID dell'utente.
     *
     * @param id l'ID da impostare
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il nome dell'utente.
     *
     * @return il nome dell'utente
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param name il nome da impostare
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce l'email dell'utente.
     *
     * @return l'email dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email dell'utente.
     *
     * @param email l'email da impostare
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return la password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'utente.
     *
     * @param password la password da impostare
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
