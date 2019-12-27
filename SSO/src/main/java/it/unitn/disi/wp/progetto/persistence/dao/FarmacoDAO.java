package it.unitn.disi.wp.progetto.persistence.dao;

import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.entities.Farmaco;

import java.util.List;

public interface  FarmacoDAO extends DAO<Farmaco, Long> {
    public List<Farmaco> getFarmaciBySuggestionNome(String suggestion)throws DAOException;
}
