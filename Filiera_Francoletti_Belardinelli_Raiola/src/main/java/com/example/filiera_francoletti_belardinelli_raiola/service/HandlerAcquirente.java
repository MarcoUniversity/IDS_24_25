package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Carrello;
import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Pagamento;
import com.example.filiera_francoletti_belardinelli_raiola.model.Payment.Ricevuta;
import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Acquirente;
import com.example.filiera_francoletti_belardinelli_raiola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandlerAcquirente {

    private Carrello shoppingCart;
    private List<Pagamento> payments;
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public HandlerAcquirente(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
        this.shoppingCart = new Carrello();
        this.payments = new ArrayList<>();
    }

    // Aggiunge un prodotto al carrello recuperandolo dal repository
    public void addProduct(Long productId) {
        Prodotto product = prodottoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + productId));
        this.shoppingCart.addProduct(product);
    }

    // Rimuove un prodotto dal carrello per id
    public void removeProduct(Long productId) {
        this.shoppingCart.removeProduct(productId);
    }

    // Esegue il pagamento, genera una ricevuta, salva il pagamento e svuota il carrello
    public Pagamento pay(Acquirente acquirente) {
        String invoiceContent = generateInvoice(this.shoppingCart);
        Ricevuta invoice = new Ricevuta(invoiceContent);
        Pagamento pagamento = new Pagamento(acquirente, this.shoppingCart, invoice);
        this.payments.add(pagamento);
        this.shoppingCart.clearProducts();
        return pagamento;
    }

    // Metodo per generare il contenuto della ricevuta basato sui prodotti nel carrello
    private String generateInvoice(Carrello cart) {
        double total = 0;
        StringBuilder sb = new StringBuilder("Ricevuta:\n");
        for (Prodotto p : cart.getProducts()) {
            sb.append("Prodotto: ").append(p.getName())
                    .append(" - Prezzo: ").append(p.getPrice()).append("\n");
            total += p.getPrice();
        }
        sb.append("Totale: ").append(total);
        return sb.toString();
    }

    public Carrello getShoppingCart() {
        return shoppingCart;
    }

    public List<Pagamento> getPayments() {
        return payments;
    }
}
