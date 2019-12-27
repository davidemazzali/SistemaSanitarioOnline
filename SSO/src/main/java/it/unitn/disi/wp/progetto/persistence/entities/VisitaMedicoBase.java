package it.unitn.disi.wp.progetto.persistence.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class VisitaMedicoBase implements Serializable {
    private long id;
    private UtenteView medicoBase;
    private UtenteView paziente;
    private Timestamp erogazione;
    private String anamnesi;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UtenteView getMedicoBase() {
        return medicoBase;
    }

    public void setMedicoBase(UtenteView medicoBase) {
        this.medicoBase = medicoBase;
    }

    public UtenteView getPaziente() {
        return paziente;
    }

    public void setPaziente(UtenteView paziente) {
        this.paziente = paziente;
    }

    public Timestamp getErogazione() {
        return erogazione;
    }

    public void setErogazione(Timestamp erogazione) {
        this.erogazione = erogazione;
    }

    public String getAnamnesi() {
        return anamnesi;
    }

    public void setAnamnesi(String anamnesi) {
        this.anamnesi = anamnesi;
    }
}
