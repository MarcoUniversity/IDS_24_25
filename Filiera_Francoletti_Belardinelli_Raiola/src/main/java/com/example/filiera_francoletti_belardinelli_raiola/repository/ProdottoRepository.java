package com.example.filiera_francoletti_belardinelli_raiola.repository;

import com.example.filiera_francoletti_belardinelli_raiola.model.Product.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findBySellerId(Long sellerId);
    List<Prodotto> findBySellerIdAndStateTrue(Long sellerId);
    List<Prodotto> findByStateFalse();
}

