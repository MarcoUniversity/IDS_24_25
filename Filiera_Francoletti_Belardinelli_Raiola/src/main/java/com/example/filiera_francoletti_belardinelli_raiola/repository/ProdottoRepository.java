package com.example.filiera_francoletti_belardinelli_raiola.repository;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findByStateTrue(); //restituisce i prodotti verificati

    List<Prodotto> findByStateFalse(); //restituisce i prodotti non verificati

    List<Prodotto> findBySellerIdAndStateTrue(Long sellerId);//restituisce i prodotti verificati di un venditore
}

