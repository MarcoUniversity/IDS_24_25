package com.example.filiera_francoletti_belardinelli_raiola.model.Administration;
import jakarta.persistence.*;


@Entity
public class Curatore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Curatore() {
    }

    public Curatore(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
