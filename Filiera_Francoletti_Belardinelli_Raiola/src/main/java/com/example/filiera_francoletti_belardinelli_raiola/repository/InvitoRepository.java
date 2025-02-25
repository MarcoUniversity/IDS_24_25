package com.example.filiera_francoletti_belardinelli_raiola.repository;

import com.example.filiera_francoletti_belardinelli_raiola.model.events.Invito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitoRepository extends JpaRepository<Invito, Long> {
}

