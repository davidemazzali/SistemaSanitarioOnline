package it.unitn.disi.wp.progetto.persistence.dao;

import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.entities.Visita;

import java.util.List;

public interface VisitaDAO extends DAO<Visita, Long> {
    public List<Visita> getVisiteBySuggestionNome(String suggestion)throws DAOException;
}
