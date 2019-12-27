/*
 * AA 2018-2019
 * Introduction to Web Programming
 * Lab 08 - Commons - DAO interface
 * UniTN
 */
package it.unitn.disi.wp.progetto.persistence.dao.factories;

import it.unitn.disi.wp.progetto.persistence.dao.DAO;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOFactoryException;

public interface DAOFactory {

    public void shutdown();
    public <DAO_CLASS extends DAO> DAO_CLASS getDAO(Class<DAO_CLASS> daoInterface) throws DAOFactoryException;
}
