package com.example.filiera_francoletti_belardinelli_raiola.model.Users;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Rappresenta una notifica destinata ad un utente.
 * <p>
 * La notifica contiene un messaggio, la data in cui è stata generata e l'ID dell'utente (generic o acquirente)
 * a cui è destinata.
 * </p>
 */
@Entity
public class Notifica {

    /**
     * Identificativo univoco della notifica.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Messaggio della notifica.
     */
    private String messaggio;

    /**
     * Data e ora in cui la notifica è stata generata.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNotifica;

    /**
     * Identificativo dell'utente a cui è destinata la notifica.
     */
    private Long utenteId;

    /**
     * Costruttore di default.
     */
    public Notifica() {}

    /**
     * Costruttore che inizializza una nuova notifica con il messaggio e l'ID dell'utente specificato.
     * Imposta automaticamente la data della notifica all'istante della creazione.
     *
     * @param messaggio il messaggio della notifica
     * @param utenteId  l'ID dell'utente destinatario della notifica
     */
    public Notifica(String messaggio, Long utenteId) {
        this.messaggio = messaggio;
        this.utenteId = utenteId;
        this.dataNotifica = new Date();
    }

    /**
     * Restituisce l'ID della notifica.
     *
     * @return l'ID della notifica
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta l'ID della notifica.
     *
     * @param id l'ID da impostare
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il messaggio della notifica.
     *
     * @return il messaggio della notifica
     */
    public String getMessaggio() {
        return messaggio;
    }

    /**
     * Imposta il messaggio della notifica.
     *
     * @param messaggio il messaggio da impostare
     */
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    /**
     * Restituisce la data della notifica.
     *
     * @return la data e ora della notifica
     */
    public Date getDataNotifica() {
        return dataNotifica;
    }

    /**
     * Imposta la data della notifica.
     *
     * @param dataNotifica la data da impostare
     */
    public void setDataNotifica(Date dataNotifica) {
        this.dataNotifica = dataNotifica;
    }

    /**
     * Restituisce l'ID dell'utente destinatario della notifica.
     *
     * @return l'ID dell'utente
     */
    public Long getUtenteId() {
        return utenteId;
    }

    /**
     * Imposta l'ID dell'utente destinatario della notifica.
     *
     * @param utenteId l'ID dell'utente da impostare
     */
    public void setUtenteId(Long utenteId) {
        this.utenteId = utenteId;
    }
}
