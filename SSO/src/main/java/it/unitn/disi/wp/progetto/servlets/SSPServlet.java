package it.unitn.disi.wp.progetto.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SSPServlet", urlPatterns = {"/ssp"})
public class SSPServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String homePage = request.getServletContext().getInitParameter("homeSsp");
        getServletContext().getRequestDispatcher(homePage).forward(request, response);
    }
}
