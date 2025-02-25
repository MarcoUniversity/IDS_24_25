package com.example.filiera_francoletti_belardinelli_raiola.repository;

import com.example.filiera_francoletti_belardinelli_raiola.model.social.ContenutoSocial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<ContenutoSocial, Long> {
}
