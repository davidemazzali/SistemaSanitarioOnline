package it.unitn.disi.wp.progetto.api;

import it.unitn.disi.wp.progetto.commons.Utilities;
import it.unitn.disi.wp.progetto.persistence.dao.FotoDAO;
import it.unitn.disi.wp.progetto.persistence.dao.UtenteDAO;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOFactoryException;
import it.unitn.disi.wp.progetto.persistence.entities.Foto;
import it.unitn.disi.wp.progetto.persistence.entities.Utente;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import static it.unitn.disi.wp.progetto.commons.Utilities.*;

@Path("utenti")
public class UtenteApi extends Api {

    @GET
    @Path("{idutente}/foto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFoto(@PathParam("idutente") long idUtente) {

        Response res;

        try {
            UtenteDAO utenteDAO = daoFactory.getDAO(UtenteDAO.class);
            Utente utente = utenteDAO.getByPrimaryKey(idUtente);

            if(utente != null) {
                FotoDAO fotoDAO = daoFactory.getDAO(FotoDAO.class);
                List<Foto> foto = fotoDAO.getFotoByUtente(idUtente);
                String jsonResult = gson.toJson(foto);
                res = Response.ok(jsonResult).build();
            }
            else {
                res = notFoundResponse;
            }
        } catch (DAOFactoryException e) {
            res = daoFactoryErrorResponse;
        } catch (DAOException e) {
            res = daoErrorResponse;
        }

        return res;
    }

    @POST
    @Path("{idutente}/foto")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addNuovaFoto(@PathParam("idutente") long idUtente,
                                 @FormDataParam("foto") InputStream fotoIS,
                                 @FormDataParam("foto") FormDataContentDisposition fotoDetail) {
        if(fotoIS == null || fotoDetail == null) {
            return badRequestResponse;
        }

        Response res;
        if(fotoDetail.getFileName().toLowerCase().matches(Utilities.USER_IMAGE_EXT_REGEX)) {
            try {
                UtenteDAO utenteDAO = daoFactory.getDAO(UtenteDAO.class);
                Utente utente = utenteDAO.getByPrimaryKey(idUtente);

                if(utente != null) {
                    FotoDAO fotoDAO = daoFactory.getDAO(FotoDAO.class);
                    fotoDAO.addNewFoto(idUtente, new Timestamp(System.currentTimeMillis()));

                    List<Foto> fotoUtente = fotoDAO.getFotoByUtente(idUtente);
                    long maxFound = 0;
                    for (Foto foto : fotoUtente) {
                        if (foto.getId() > maxFound) {
                            maxFound = foto.getId();
                        }
                    }

                    String path = context.getRealPath(File.separator + Utilities.USER_IMAGES_FOLDER + File.separator + idUtente + File.separator + maxFound + "." + Utilities.USER_IMAGE_EXT);
                    System.out.println(path);
                    try {
                        BufferedImage inFoto = ImageIO.read(fotoIS);
                        BufferedImage outFoto = new BufferedImage(Utilities.USER_IMAGES_WIDTH, Utilities.USER_IMAGES_HEIGHT, inFoto.getType());
                        Graphics2D g2d = outFoto.createGraphics();
                        g2d.drawImage(inFoto, 0, 0, Utilities.USER_IMAGES_WIDTH, Utilities.USER_IMAGES_HEIGHT, null);
                        g2d.dispose();
                        ImageIO.write(outFoto, Utilities.USER_IMAGE_EXT, new File(path));
                        fotoIS.close();
                        res = createdResponse;
                    } catch (IOException e) { //500
                        fotoDAO.deleteFoto(maxFound);
                        e.printStackTrace();
                        res = ioErrorResponse;
                    }
                }
                else {
                    res = notFoundResponse;
                }
            } catch (DAOFactoryException e) {
                res = daoFactoryErrorResponse;
            } catch (DAOException e) {
                res = daoErrorResponse;
            }
        }
        else {
            res = badRequestResponse;
        }

        return res;
    }

    @PUT
    @Path("{idutente}/password")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response changePassword(@PathParam("idutente") long idUtente,
                                   @FormParam("password") String password) {
        if(password == null) {
            return badRequestResponse;
        }

        Response res;
        try {
            UtenteDAO utenteDAO = daoFactory.getDAO(UtenteDAO.class);
            Utente utente = utenteDAO.getByPrimaryKey(idUtente);

            if(utente != null) {
                String hash = Utilities.sha512(password, utente.getSalt());
                utenteDAO.updatePassword(idUtente, hash, utente.getSalt());
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.setAttribute("utente", utenteDAO.getByPrimaryKey(utente.getId()));
                }
                res = noContentResponse;
            }
            else {
                res = notFoundResponse;
            }
        } catch (DAOFactoryException e) {
            res = daoFactoryErrorResponse;
        } catch (DAOException e) {
            res = daoErrorResponse;
        }

        return res;
    }
}
