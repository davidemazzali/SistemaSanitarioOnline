package it.unitn.disi.wp.progetto.servlets;

import static it.unitn.disi.wp.progetto.commons.Utilities.*;
import it.unitn.disi.wp.progetto.persistence.dao.TokenRememberMeDAO;
import it.unitn.disi.wp.progetto.persistence.dao.UtenteDAO;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOFactoryException;
import it.unitn.disi.wp.progetto.persistence.dao.factories.DAOFactory;
import it.unitn.disi.wp.progetto.persistence.dao.factories.jdbc.JDBCDAOFactory;
import it.unitn.disi.wp.progetto.persistence.entities.TokenRememberMe;
import it.unitn.disi.wp.progetto.persistence.entities.Utente;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name="LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private String email;
    private String password;
    private String rememberMe;
    private String loginUrl = "/login.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        email = request.getParameter("email");
        password = request.getParameter("password");
        rememberMe = request.getParameter("remember_me");

        Utente utente = doAuthN(email, password);

        if (utente == null){
            request.setAttribute("error", "Username o Password errati. Riprovare");
            doGet(request, response);
        }else{
            setCookieRM(utente, response);
            createSessionAndDispatch(request, response, utente);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Utente utente = null;

        String msg = request.getParameter("rp") == null ? "" : "La password è stata modificata con successo";
        request.setAttribute("msg", msg);

        Cookie c = getCookieByName(request.getCookies(), "rememberMe");
        String token = c != null ? c.getValue(): null;
        utente = token != null ? getUtentebyToken(token) : null;

        if (utente != null) {
            System.out.println("autenticato, redirect alla home");
            createSessionAndDispatch(request, response, utente);
        }else{
            getServletContext().getRequestDispatcher(loginUrl).forward(request, response);
        }
    }

    private Utente checkUsername(String email){
        Utente utente = null;
        try {
            DAOFactory daoFactory = JDBCDAOFactory.getInstance();
            UtenteDAO userdao = daoFactory.getDAO(UtenteDAO.class);
            utente = userdao.getUserByEmail(email);
        } catch (DAOFactoryException | DAOException ex) {
            ex.printStackTrace();
        }

        return utente;
    }

    private void createSessionAndDispatch(HttpServletRequest request, HttpServletResponse response, Utente utente) throws ServletException, IOException{

        HttpSession s = request.getSession();
        s.setAttribute("utente", utente);
        String ruolo = utente.getRuolo();
        String homeUrl;
        switch (ruolo){
            case PAZIENTE_RUOLO:
                homeUrl = "/pages/homeP.jsp";
                break;
            case MEDICO_BASE_RUOLO:
                homeUrl = "/pages/homeMB.jsp";
                break;
            case MEDICO_SPECIALISTA_RUOLO:
                homeUrl = "/pages/homeMS.jsp";
                break;
            case FARMACIA_RUOLO:
                homeUrl = "/pages/homeF.jsp";
                break;
            case SSN_RUOLO:
                homeUrl = "/pages/homeSSN.jsp";
                break;
            case SSP_RUOLO:
                homeUrl = "/pages/homeSSP.jsp";
                break;
            default:
                homeUrl = "/notImplementedYet.jsp";
                break;
        }

        Cookie c = getCookieByName(request.getCookies(),"JSESSIONID");
        System.out.println(c != null ? c.getValue() : "cookie JSESSIONID non esistente");

        response.sendRedirect(request.getContextPath() + homeUrl);
    }

    private boolean checkPassword(Utente utente, String givenPassword){
        boolean retval = false;
        if (utente != null){
            String savedHash = utente.getPassword();
            String computedHash = sha512(givenPassword, utente.getSalt());
            System.out.println("given pass: " + givenPassword);
            System.out.println("savedHash: " + savedHash + "\ncomputed hash: " + computedHash);
            retval = savedHash.substring(2).equals(computedHash);
        }
        return retval;
    }

    private Utente getUtentebyToken(String token){
        Utente utente = null;
        long id;
        try {
            DAOFactory daoFactory = (DAOFactory) super.getServletContext().getAttribute("daoFactory");
            TokenRememberMeDAO tokenRememberMeDAO = daoFactory.getDAO(TokenRememberMeDAO.class);
            UtenteDAO utenteDAO = daoFactory.getDAO(UtenteDAO.class);
            TokenRememberMe tokenRememberMe = tokenRememberMeDAO.getTokenByToken(sha512(token));
            if(tokenRememberMe != null){
                id = tokenRememberMe.getIdUtente();
                utente = utenteDAO.getByPrimaryKey(id);
            }
        }catch (DAOFactoryException e) {
            e.printStackTrace();
            throw new RuntimeException(new ServletException("Impossible to get dao interface for storage system"));
        } catch (DAOException e) {
            e.printStackTrace();
            throw new RuntimeException(new ServletException("Impossible to get requested data from storage system"));
        }
        return utente;
    }

    private void setCookieRM(Utente utente, HttpServletResponse response){
        if (rememberMe != null){
            String token = generaToken();
            String hashedToken = sha512(token);
            try {
                DAOFactory daoFactory = (DAOFactory) super.getServletContext().getAttribute("daoFactory");
                TokenRememberMeDAO tokenRememberMeDAO = daoFactory.getDAO(TokenRememberMeDAO.class);
                tokenRememberMeDAO.creaToken(hashedToken, utente.getId());
                Cookie rm = new Cookie("rememberMe", token);
                rm.setMaxAge(60 * 60 * 24 * 30); //un mese
                response.addCookie(rm);
            }catch (DAOFactoryException e) {
                e.printStackTrace();
                throw new RuntimeException(new ServletException("Impossible to get dao interface for storage system"));
            } catch (DAOException e) {
                e.printStackTrace();
                throw new RuntimeException(new ServletException("Impossible to get requested data from storage system"));
            }
        }
    }

    private Utente doAuthN(String email, String password){
        Utente utente = checkUsername(email);
        boolean authN = checkPassword(utente, password);
        utente = authN ? utente : null;
        return utente;
    }
}