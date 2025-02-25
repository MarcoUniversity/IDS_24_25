package com.example.filiera_francoletti_belardinelli_raiola.model.Users;

/**
 * Interfaccia che definisce il comportamento di un subscriber.
 * <p>
 * Le classi che implementano questa interfaccia devono definire il metodo {@code update()},
 * che viene invocato per notificare al subscriber l'aggiornamento di un evento o notifica.
 * </p>
 */
public interface Subscriber {
    void update();
}
