package isep.lab1;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;


public class LoggingFilter implements Filter {

    private String prefix = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        prefix = filterConfig.getInitParameter("logPrefix");
        System.out.println(prefix + " init()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("[LoggingFilter] AVANT : " + req.getRequestURL());

        chain.doFilter(request, response);   // continue vers la servlet

        System.out.println("[LoggingFilter] APRES : " + req.getRequestURI());
    }

    @Override
    public void destroy() {
        System.out.println("[LoggingFilter] destroy()");
    }
}