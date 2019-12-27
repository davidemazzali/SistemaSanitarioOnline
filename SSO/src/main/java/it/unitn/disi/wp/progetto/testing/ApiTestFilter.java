package it.unitn.disi.wp.progetto.testing;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebFilter(filterName = "ApiTestFilter", urlPatterns={"/api/topsecret"})
public class ApiTestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /*
        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession s = request.getSession(false);
        ArrayList<String> list = Collections.list(s.getAttributeNames());
        for (String k : list){
            System.out.println(k);
        }
        System.out.println("sono passato dal filtro ApiTestFilter");
        */
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
