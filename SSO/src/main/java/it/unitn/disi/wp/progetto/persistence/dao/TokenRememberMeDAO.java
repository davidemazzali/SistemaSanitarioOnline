package it.unitn.disi.wp.progetto.persistence.dao;

import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.entities.*;

public interface TokenRememberMeDAO extends DAO<TokenRememberMe, Long> {
    public boolean creaToken(String token, long idUtente) throws DAOException;
    public TokenRememberMe getTokenByToken(String Token) throws DAOException;
    public boolean deleteToken(String token) throws DAOException;

    //public boolean updateToken(String token, ) throws DAOException;
}