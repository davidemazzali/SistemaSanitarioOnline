package it.unitn.disi.wp.progetto.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="FarmaciaServlet", urlPatterns = {"/farmacia"})
public class FarmaciaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String homePage = request.getServletContext().getInitParameter("homeFarmacia");
        getServletContext().getRequestDispatcher(homePage).forward(request, response);
    }
}
