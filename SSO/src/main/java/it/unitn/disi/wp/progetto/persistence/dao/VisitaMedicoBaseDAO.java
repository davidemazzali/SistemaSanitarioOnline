package it.unitn.disi.wp.progetto.persistence.dao;

import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.entities.VisitaMedicoBase;
import java.sql.Timestamp;
import java.util.List;

public interface  VisitaMedicoBaseDAO extends DAO<VisitaMedicoBase, Long> {
    public boolean creaVisitaMedicoBase(long medicoBase, long paziente, Timestamp erogaz, String anamnesi) throws DAOException;
    public List<VisitaMedicoBase> getVisiteBaseByPaziente(long id) throws  DAOException; //l'id è del paziente
}
