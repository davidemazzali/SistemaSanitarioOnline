package it.unitn.disi.wp.progetto.persistence.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class ElemReportProv implements Serializable {
    private Timestamp emissione;
    private String cfMedico;
    private String nomeMedico;
    private String cognomeMedico;
    private String cfPaziente;
    private String nomePaziente;
    private String cognomePaziente;
    private String farmaco;
    private double prezzo;
    private String farmacia;

    public Timestamp getEmissione() {
        return emissione;
    }

    public void setEmissione(Timestamp emissione) {
        this.emissione = emissione;
    }

    public String getCfMedico() {
        return cfMedico;
    }

    public void setCfMedico(String cfMedico) {
        this.cfMedico = cfMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCognomeMedico() {
        return cognomeMedico;
    }

    public void setCognomeMedico(String cognomeMedico) {
        this.cognomeMedico = cognomeMedico;
    }

    public String getCfPaziente() {
        return cfPaziente;
    }

    public void setCfPaziente(String cfPaziente) {
        this.cfPaziente = cfPaziente;
    }

    public String getNomePaziente() {
        return nomePaziente;
    }

    public void setNomePaziente(String nomePaziente) {
        this.nomePaziente = nomePaziente;
    }

    public String getCognomePaziente() {
        return cognomePaziente;
    }

    public void setCognomePaziente(String cognomePaziente) {
        this.cognomePaziente = cognomePaziente;
    }

    public String getFarmaco() {
        return farmaco;
    }

    public void setFarmaco(String farmaco) {
        this.farmaco = farmaco;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(String farmacia) {
        this.farmacia = farmacia;
    }
}
