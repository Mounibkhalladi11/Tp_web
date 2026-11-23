package isep.lab1;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/ListItems")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        boolean connected = session != null && session.getAttribute("user") != null;

        if (connected) {
            System.out.println("[SecurityFilter] AVANT : accès autorisé");
            chain.doFilter(request, response);
            System.out.println("[SecurityFilter] APRES");
        } else {
            System.out.println("[SecurityFilter] accès REFUSE -> redirection login.html");
            res.sendRedirect(req.getContextPath() + "/login.html");
            // pas de chain.doFilter()
        }
    }
}