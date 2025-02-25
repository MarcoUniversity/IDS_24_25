package com.example.filiera_francoletti_belardinelli_raiola.repository;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    // Restituisce tutti i prodotti verificati (state == true)
    List<Prodotto> findByStateTrue();

    // Restituisce tutti i prodotti non verificati (state == false)
    List<Prodotto> findByStateFalse();

    // Restituisce i prodotti verificati per un venditore specifico
    List<Prodotto> findBySellerIdAndStateTrue(Long sellerId);
}

