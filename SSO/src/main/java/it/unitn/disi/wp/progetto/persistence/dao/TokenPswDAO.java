package it.unitn.disi.wp.progetto.persistence.dao;

import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.entities.*;

public interface TokenPswDAO extends DAO<TokenPsw, Long> {
    public boolean creaToken(String token, long idUtente) throws DAOException;
    public TokenPsw getTokenByToken(String Token) throws DAOException;
    public boolean deleteToken(long id) throws DAOException;

    //public boolean updateToken(String token, ) throws DAOException;
}