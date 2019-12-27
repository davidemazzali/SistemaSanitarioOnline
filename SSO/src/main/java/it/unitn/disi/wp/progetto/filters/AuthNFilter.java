package it.unitn.disi.wp.progetto.filters;

import it.unitn.disi.wp.progetto.commons.Utilities;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import static it.unitn.disi.wp.progetto.commons.Utilities.urlIsLike;

@WebFilter(filterName = "AuthNFilter")
public class AuthNFilter implements Filter {

    private List<String> excludedUrls;

    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        String urls = context.getInitParameter("excludedurls");
        excludedUrls = Arrays.asList(urls.split("[\\n\\t ]+"));
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        URL url = new URL(httpRequest.getRequestURL().toString());
        String field = url.getPath().substring(httpRequest.getContextPath().length() + 1);

        if(!Utilities.urlIsLike(field, excludedUrls)) {
            HttpSession s = httpRequest.getSession(false);
            boolean isAuthenticated = s != null && !s.isNew() && s.getAttribute("utente") != null;

            if (isAuthenticated) {
                System.out.println("Utente autenticato");
                request.setAttribute("isAuthenticated", true);
                chain.doFilter(request, response);
            } else {
                System.out.println("login non effettuato. Redirect a LoginServlet");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
            }
        }else {
            chain.doFilter(request, response);
        }
    }

}
