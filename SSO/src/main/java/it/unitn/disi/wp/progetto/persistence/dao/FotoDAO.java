package it.unitn.disi.wp.progetto.persistence.dao;

import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.entities.Foto;

import java.sql.Timestamp;
import java.util.List;

public interface  FotoDAO extends DAO<Foto, Long> {
    public boolean addNewFoto(long idUtente, Timestamp caricamento) throws DAOException; // solo inserimento nel db
    public List<Foto> getFotoByUtente(long idUtente) throws DAOException;
    public boolean deleteFoto(long id) throws DAOException;
}
