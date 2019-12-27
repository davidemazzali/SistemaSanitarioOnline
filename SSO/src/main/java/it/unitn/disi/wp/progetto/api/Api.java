package it.unitn.disi.wp.progetto.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unitn.disi.wp.progetto.persistence.dao.factories.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

public abstract class Api {
    protected DAOFactory daoFactory;
    protected Gson gson;
    protected ServletContext context;

    @Context
    protected HttpServletRequest request;
    @Context
    protected HttpServletResponse response;

    public Api() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.S").create();
    }

    @Context
    public void setServletContext(ServletContext servletContext) {
        if (servletContext != null) {
            context = servletContext;
            daoFactory = (DAOFactory) servletContext.getAttribute("daoFactory");
            if (daoFactory == null) {
                throw new RuntimeException(new ServletException("Impossible to get dao factory for storage system"));
            }
        }
    }

}
