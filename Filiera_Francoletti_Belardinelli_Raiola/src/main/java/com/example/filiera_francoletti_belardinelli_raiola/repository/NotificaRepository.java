package com.example.filiera_francoletti_belardinelli_raiola.repository;

import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificaRepository extends JpaRepository<Notifica, Long> {
    List<Notifica> findByUtenteId(Long utenteId);
}
