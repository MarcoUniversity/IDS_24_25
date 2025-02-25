package com.example.filiera_francoletti_belardinelli_raiola.service;

import com.example.filiera_francoletti_belardinelli_raiola.model.Users.Notifica;
import com.example.filiera_francoletti_belardinelli_raiola.repository.NotificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandlerNotifica {

    private final NotificaRepository notificaRepository;

    @Autowired
    public HandlerNotifica(NotificaRepository notificaRepository) {
        this.notificaRepository = notificaRepository;
    }

    public Notifica creaNotifica(String messaggio, Long utenteId) {
        Notifica notifica = new Notifica(messaggio, utenteId);
        System.out.println("Creazione notifica per utente " + utenteId + ": " + messaggio);
        return notificaRepository.save(notifica);
    }

    public List<Notifica> getNotifichePerUtente(Long utenteId) {
        return notificaRepository.findByUtenteId(utenteId);
    }
}
