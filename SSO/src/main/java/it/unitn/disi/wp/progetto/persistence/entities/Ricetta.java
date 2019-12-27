package it.unitn.disi.wp.progetto.persistence.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Ricetta implements Serializable {

    private long id;
    private Farmaco farmaco;
    private UtenteView medicoBase;
    private UtenteView paziente;
    private UtenteView farmacia;
    private Timestamp emissione;
    private Timestamp evasione;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Farmaco getFarmaco() {
        return farmaco;
    }

    public void setFarmaco(Farmaco farmaco) {
        this.farmaco = farmaco;
    }

    public UtenteView getMedicoBase() {
        return medicoBase;
    }

    public void setMedicoBase(UtenteView medicoBase) {
        this.medicoBase = medicoBase;
    }

    public UtenteView getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(UtenteView farmacia) {
        this.farmacia = farmacia;
    }

    public Timestamp getEmissione() {
        return emissione;
    }

    public void setEmissione(Timestamp Emissione) {
        this.emissione = Emissione;
    }

    public Timestamp getEvasione() {
        return evasione;
    }

    public void setEvasione(Timestamp evasione) {
        this.evasione = evasione;
    }

    public UtenteView getPaziente() {
        return paziente;
    }

    public void setPaziente(UtenteView paziente) {
        this.paziente = paziente;
    }
}
