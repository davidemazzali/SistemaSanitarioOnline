package it.unitn.disi.wp.progetto.persistence.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class VisitaMedicoSpecialista implements Serializable {
    private long id;
    private UtenteView medicoSpecialista;
    private UtenteView medicoBase;
    private UtenteView paziente;
    private Timestamp prescrizione;
    private Timestamp erogazione;
    private String anamnesi;
    private Visita visita;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UtenteView getMedicoSpecialista() {
        return medicoSpecialista;
    }

    public void setMedicoSpecialista(UtenteView medicoSpecialista) {
        this.medicoSpecialista = medicoSpecialista;
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

    public Timestamp getPrescrizione() {
        return prescrizione;
    }

    public void setPrescrizione(Timestamp prescrizione) {
        this.prescrizione = prescrizione;
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

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }
}
