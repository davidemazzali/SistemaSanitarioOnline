package it.unitn.disi.wp.progetto.persistence.dao.jdbc;

import it.unitn.disi.wp.progetto.persistence.dao.ProvinciaDAO;
 import it.unitn.disi.wp.progetto.persistence.dao.UtenteDAO;
 import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.entities.Provincia;
import it.unitn.disi.wp.progetto.persistence.entities.Utente;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCProvinciaDAO extends JDBCDAO<Provincia, String> implements ProvinciaDAO{

    public JDBCProvinciaDAO(Connection con) {
        super(con);
    }

    @Override
    public Long getCount() throws DAOException {
        PreparedStatement stmt = null;
        try {
            stmt = CON.prepareStatement("SELECT count(*) FROM provincia");
            ResultSet counter = stmt.executeQuery();
            if (counter.next()) {
                return counter.getLong(1); // 1-based indexing
            }

        } catch (SQLException ex) {
            throw new DAOException("Impossible to count users", ex);
        }

        return 0L;
    }

    @Override
    public Provincia getByPrimaryKey(String primaryKey) throws DAOException {
        if (primaryKey == null) {
            throw new DAOException("primaryKey is null");
        }
        try (PreparedStatement stm = CON.prepareStatement("SELECT * FROM provincia WHERE id = ?")) {
            stm.setString(1, primaryKey);

            try (ResultSet rs = stm.executeQuery()) {
                Provincia prov = null;

                if(rs.next()) {
                    prov = makeProvinciaFromRs(rs);
                }

                return prov;
            }
        } catch (SQLException ex) {
            throw new DAOException("Impossible to get the user for the passed primary key", ex);
        }
    }


    @Override
    public List<Provincia> getAll() throws DAOException {
        List<Provincia> province = new ArrayList<>();

        try (PreparedStatement stm = CON.prepareStatement("SELECT * FROM provincia ORDER BY nome")) {

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    province.add(makeProvinciaFromRs(rs));
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Impossible to get the list of users", ex);
        }

        return province;
    }

    private Provincia makeProvinciaFromRs(ResultSet rs) throws SQLException {
        Provincia provincia = new Provincia();
        provincia.setId(rs.getString("id"));
        provincia.setNome(rs.getString("nome"));

        return provincia;
    }
}
