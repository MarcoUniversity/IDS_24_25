package com.example.filiera_francoletti_belardinelli_raiola.model.Users;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_utente", discriminatorType = DiscriminatorType.STRING)
public class UtenteGenerico implements Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    public UtenteGenerico() {}

    public UtenteGenerico(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Implementazione del metodo di Subscriber
    @Override
    public void update() {
        // Azione che l'utente fa quando riceve la notifica.
        // Ad esempio, stampare un messaggio, aggiornare un contatore di notifiche, ecc.
        System.out.println("[" + this.name + "] Ha ricevuto una notifica: nuovo evento creato!");
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
