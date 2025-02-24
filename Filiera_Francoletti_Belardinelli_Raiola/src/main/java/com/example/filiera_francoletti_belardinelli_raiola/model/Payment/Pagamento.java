package com.example.filiera_francoletti_belardinelli_raiola.model.Payment;


import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Acquirente;
import jakarta.persistence.*;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Acquirente payer;

    @OneToOne(cascade = CascadeType.ALL)
    private Carrello products;

    @OneToOne(cascade = CascadeType.ALL)
    private Ricevuta invoice;

    public Pagamento() {}

    public Pagamento(Acquirente payer, Carrello products, Ricevuta invoice) {
        this.payer = payer;
        this.products = products;
        this.invoice = invoice;
    }

    // Getters and setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Acquirente getPayer() { return payer; }
    public void setPayer(Acquirente payer) { this.payer = payer; }
    public Carrello getProducts() { return products; }
    public void setProducts(Carrello products) { this.products = products; }
    public Ricevuta getInvoice() { return invoice; }
    public void setInvoice(Ricevuta invoice) { this.invoice = invoice; }
}
