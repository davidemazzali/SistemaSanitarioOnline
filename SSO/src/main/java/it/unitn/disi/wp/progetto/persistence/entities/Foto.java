package it.unitn.disi.wp.progetto.persistence.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Foto implements Serializable {
    private long id;
    private Timestamp caricamento;
    private UtenteView paziente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCaricamento() {
        return caricamento;
    }

    public void setCaricamento(Timestamp caricamento) {
        this.caricamento = caricamento;
    }

    public UtenteView getPaziente() {
        return paziente;
    }

    public void setPaziente(UtenteView Paziente) {
        this.paziente = Paziente;
    }
}
