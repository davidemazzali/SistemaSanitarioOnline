package it.unitn.disi.wp.progetto.persistence.dao.jdbc;

import java.sql.Connection;
import it.unitn.disi.wp.progetto.persistence.dao.DAO;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOFactoryException;
import java.util.HashMap;

public abstract class JDBCDAO<ENTITY_CLASS, PRIMARY_KEY_CLASS> implements DAO<ENTITY_CLASS, PRIMARY_KEY_CLASS> {
    protected final Connection CON;
    protected final HashMap<Class, DAO> FRIEND_DAOS;

    protected JDBCDAO(Connection con) {
        super();
        this.CON = con;
        FRIEND_DAOS = new HashMap<>();
    }

    @Override
    public <DAO_CLASS extends DAO> DAO_CLASS getDAO(Class<DAO_CLASS> daoClass) throws DAOFactoryException {
        return (DAO_CLASS) FRIEND_DAOS.get(daoClass);
    }
}
