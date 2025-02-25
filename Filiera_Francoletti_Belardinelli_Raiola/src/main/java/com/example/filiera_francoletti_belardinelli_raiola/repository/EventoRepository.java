package com.example.filiera_francoletti_belardinelli_raiola.repository;

import com.example.filiera_francoletti_belardinelli_raiola.model.events.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCreatorId(Long creatorId);

}

