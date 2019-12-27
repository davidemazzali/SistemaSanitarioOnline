package it.unitn.disi.wp.progetto.persistence.dao;

import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.entities.Esame;

import java.util.List;

public interface EsameDAO extends DAO<Esame, Long> {
    public List<Esame> getEsamiBySuggestionNome(String suggestion)throws DAOException;
}
