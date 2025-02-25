package com.example.filiera_francoletti_belardinelli_raiola.model.Users;

import jakarta.persistence.Entity;

/**
 * Rappresenta un acquirente nel sistema.
 * <p>
 * Questa classe estende {@link UtenteGenerico} per ereditare le proprietà comuni a tutti gli utenti.
 * È annotata con {@code @Entity} per essere gestita dal framework JPA.
 * </p>
 */
@Entity
public class Acquirente extends UtenteGenerico {
    /**
     * Costruttore di default.
     * Necessario per il corretto funzionamento di JPA.
     */
    public Acquirente() {
        super();
    }

}
