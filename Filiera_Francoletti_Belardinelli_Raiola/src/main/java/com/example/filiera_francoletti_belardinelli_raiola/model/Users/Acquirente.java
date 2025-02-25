package com.example.filiera_francoletti_belardinelli_raiola.model.Users;

import jakarta.persistence.Entity;

@Entity
public class Acquirente extends UtenteGenerico {

    // Non serve definire nuovamente l'id se già presente in UtenteGenerico

    public Acquirente() {
        super();
    }

    public Acquirente(String name, String email, String password) {
        super(name, email, password);
    }

    // Altri eventuali campi specifici di Acquirente
}
