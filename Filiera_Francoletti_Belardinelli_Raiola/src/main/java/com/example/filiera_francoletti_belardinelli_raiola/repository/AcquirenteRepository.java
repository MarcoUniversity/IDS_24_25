package com.example.filiera_francoletti_belardinelli_raiola.repository;

import com.example.filiera_francoletti_belardinelli_raiola.model.Events.AnimatoreDellaFiliera;
import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {
}
