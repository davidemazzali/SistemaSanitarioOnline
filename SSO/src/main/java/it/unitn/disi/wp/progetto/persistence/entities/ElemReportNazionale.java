package it.unitn.disi.wp.progetto.persistence.entities;

import java.io.Serializable;

public class ElemReportNazionale implements Serializable {
    private String provincia;
    private String cfMedico;
    private String nomeMedico;
    private String cognomeMedico;
    private double spesa;

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
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

    public double getSpesa() {
        return spesa;
    }

    public void setSpesa(double spesa) {
        this.spesa = spesa;
    }
}
