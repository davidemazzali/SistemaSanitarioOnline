package it.unitn.disi.wp.progetto.persistence.dao.exceptions;

public class DAOFactoryException extends Exception {

    public DAOFactoryException() {
        super();
    }

    public DAOFactoryException(String message) {
        super(message);
    }

    public DAOFactoryException(Throwable cause) {
        super(cause);
    }

    public DAOFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
