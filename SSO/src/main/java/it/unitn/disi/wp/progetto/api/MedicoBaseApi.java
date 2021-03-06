package it.unitn.disi.wp.progetto.api;

import it.unitn.disi.wp.progetto.commons.Utilities;
import it.unitn.disi.wp.progetto.persistence.dao.UtenteDAO;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOFactoryException;
import it.unitn.disi.wp.progetto.persistence.entities.Utente;
import it.unitn.disi.wp.progetto.persistence.entities.UtenteView;

import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static it.unitn.disi.wp.progetto.commons.Utilities.*;

@Path("medicibase")
public class MedicoBaseApi extends Api {

    @GET
    @Path("{idmedico}/pazienti")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPazientiByMedicoBase(@PathParam("idmedico") long idMedicoBase) {
        Response res;

        try {

            UtenteDAO utenteDAO = daoFactory.getDAO(UtenteDAO.class);
            Utente medico = utenteDAO.getByPrimaryKey(idMedicoBase);

            if(medico != null) {
                List<Utente> list = utenteDAO.getPazientiByMedicoBase(idMedicoBase);
                ArrayList<UtenteView> jsonList = new ArrayList<>();
                for (Utente user : list) {
                    jsonList.add(Utilities.fromUtenteToUtenteView(user));
                }
                String jsonResult = gson.toJson(jsonList);
                res = Response.ok(jsonResult).build();
            }
            else {
                return notFoundResponse;
            }
        }catch (DAOFactoryException e) {
            res = daoFactoryErrorResponse;
        } catch (DAOException e) {
            res = daoErrorResponse;
        }

        return res;
    }
}
