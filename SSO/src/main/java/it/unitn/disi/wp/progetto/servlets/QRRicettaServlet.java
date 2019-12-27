package it.unitn.disi.wp.progetto.servlets;

import it.unitn.disi.wp.progetto.persistence.dao.RicettaDAO;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOFactoryException;
import it.unitn.disi.wp.progetto.persistence.dao.factories.DAOFactory;
import it.unitn.disi.wp.progetto.persistence.entities.Ricetta;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet(name = "QRRicettaServlet", urlPatterns = {"/docs/ricette"})
public class QRRicettaServlet extends HttpServlet {
    private RicettaDAO ricettaDAO;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = (DAOFactory) super.getServletContext().getAttribute("daoFactory");
        if (daoFactory != null) {
            try {
                ricettaDAO = daoFactory.getDAO(RicettaDAO.class);
            } catch (DAOFactoryException ex) {
                throw new ServletException("Impossible to get dao factory for storage system");
            }
        }
        else {
            throw new ServletException("Impossible to get dao factory for storage system");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if(idStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        long id = Long.parseLong(idStr);

        response.setContentType("image/jpeg");
        String fileName = "ricetta_" + id + ".jpg";
        response.addHeader("Content-Disposition", "inline; filename=" + fileName);

        try {
            Ricetta ricetta = ricettaDAO.getByPrimaryKey(id);

            String content = ricetta.getMedicoBase().getId() + "-" +
                    ricetta.getPaziente().getCodiceFiscale() + "-" +
                    ricetta.getEmissione() + "-" +
                    ricetta.getId() +"-" +
                    ricetta.getFarmaco().getDescrizione();

            ByteArrayOutputStream qrOut = QRCode.from(content).to(ImageType.JPG).stream();
            qrOut.writeTo(response.getOutputStream());
        } catch (DAOException exc) {
            throw new ServletException("Impossibile to get the needed data from storage system", exc);
        }
    }
}
