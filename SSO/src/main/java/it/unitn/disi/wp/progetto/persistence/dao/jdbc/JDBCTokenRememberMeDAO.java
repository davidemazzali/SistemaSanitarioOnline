package it.unitn.disi.wp.progetto.persistence.dao.jdbc;


import it.unitn.disi.wp.progetto.persistence.dao.TokenRememberMeDAO;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.entities.TokenPsw;
import it.unitn.disi.wp.progetto.persistence.entities.TokenRememberMe;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JDBCTokenRememberMeDAO extends JDBCDAO<TokenRememberMe, Long> implements TokenRememberMeDAO {

    public JDBCTokenRememberMeDAO(Connection con) {
        super(con);
    }

    @Override
    public Long getCount() throws DAOException {
        PreparedStatement stmt = null;
        try {
            stmt = CON.prepareStatement("SELECT count(*) FROM token_remember_me;");
            ResultSet counter = stmt.executeQuery();
            if (counter.next()) {
                return counter.getLong(1); // 1-based indexing
            }

        } catch (SQLException ex) {
            throw new DAOException("Impossible to count users", ex);
        }

        return 0L;
    }

    //CREDO CHE QUESTO METODO SIA DA RIFARE
    @Override
    public TokenRememberMe getByPrimaryKey(Long primaryKey) throws DAOException {
        if (primaryKey == null) {
            throw new DAOException("primaryKey is null");
        }
        try (PreparedStatement stm = CON.prepareStatement("SELECT * FROM token_remember_me WHERE id = ?;")) {
            stm.setLong(1, primaryKey);

            try (ResultSet rs = stm.executeQuery()) {
                TokenRememberMe tokenRememberMe = null;

                if(rs.next()) {
                    tokenRememberMe = makeTokenFromRs(rs);
                }

                return tokenRememberMe;
            }
        } catch (SQLException ex) {
            throw new DAOException("Impossible to get the user for the passed primary key", ex);
        }
    }

    @Override
    public List<TokenRememberMe> getAll() throws DAOException {
        List<TokenRememberMe> tokenRememberMe = new ArrayList<>();

        try (PreparedStatement stm = CON.prepareStatement("SELECT * FROM token_psw;")) {

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    tokenRememberMe.add(makeTokenFromRs(rs));
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Impossible to get the list", ex);
        }

        return tokenRememberMe;
    }

    @Override
    public boolean creaToken(String token, long idUtente) throws DAOException{
        boolean noErr = false;

        try (PreparedStatement stm = CON.prepareStatement("INSERT INTO token_remember_me" +
                "(token, idutente) VALUES (?,?);")){
            byte[] bytes = new BigInteger(token, 16).toByteArray();
            if (bytes.length == 65){
                bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
            }
            stm.setBytes(1, bytes); // 1-based indexing
            stm.setLong(2, idUtente); // 1-based indexing

            stm.executeUpdate();
            noErr = true;
        } catch (SQLException ex) {
            throw new DAOException("Impossible to insert", ex);
        }

        return noErr;

    }

    @Override
    public TokenRememberMe getTokenByToken(String Token) throws DAOException{
        if ((Token == null)) {
            throw new DAOException("Email is mandatory fields", new NullPointerException("email is null"));
        }

        try (PreparedStatement stm = CON.prepareStatement("SELECT * FROM token_remember_me WHERE token = ?;")) {

            byte[] bytes = new BigInteger(Token, 16).toByteArray();
            if (bytes.length == 65){
                bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
            }
            stm.setBytes(1, bytes); // 1-based indexing

            try (ResultSet rs = stm.executeQuery()) {
                TokenRememberMe tokenRememberMe = null;
                if(rs.next()) {
                    tokenRememberMe = makeTokenFromRs(rs);
                }

                return tokenRememberMe;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new DAOException("Impossible to get the user by email", ex);
        }

    }

    @Override
    public boolean deleteToken(String token) throws DAOException{
        boolean noErr = false;

        try (PreparedStatement stm = CON.prepareStatement("DELETE FROM token_remember_me WHERE token=?")){
            byte[] bytes = new BigInteger(token, 16).toByteArray();
            if (bytes.length == 65){
                bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
            }
            stm.setBytes(1, bytes); // 1-based indexing

            stm.executeUpdate();
            noErr = true;
        } catch (SQLException ex) {
            throw new DAOException("Impossible to insert", ex);
        }

        return noErr;
    }

/*
    @Override
    public boolean updateToken(long idToken, String token) throws DAOException{
        boolean noErr = false;


      //  UPDATE TOKEN SET Token='okboomer',LastEdit=NOW() WHERE Id=1;
        try (PreparedStatement stm = CON.prepareStatement("UPDATE TOKEN SET " +
                "Token=?,LastEdit=NOW() WHERE Id=?;")){
            stm.setLong(1, idToken); // 1-based indexing
            stm.setString(2, token); // 1-based indexing

            stm.executeUpdate();
            noErr = true;
        } catch (SQLException ex) {
            throw new DAOException("Impossible to update", ex);
        }

        return noErr;
    }
*/

    //QUESTO E' DA RIFARE , DAVIDE QUI IO TI EVOCO
    private TokenRememberMe makeTokenFromRs(ResultSet rs) throws SQLException {
        TokenRememberMe tokenRememberMe= new TokenRememberMe();
        tokenRememberMe.setToken(rs.getString(1));
        tokenRememberMe.setIdUtente(rs.getLong(2));
        tokenRememberMe.setLastEdit(rs.getTimestamp(3));

        return tokenRememberMe;
    }
}
