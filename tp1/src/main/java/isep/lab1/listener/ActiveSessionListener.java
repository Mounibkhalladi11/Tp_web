package isep.lab1.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class ActiveSessionListener implements HttpSessionListener {

    private static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int n = counter.incrementAndGet();
        se.getSession().getServletContext().setAttribute("activeSessions", n);
        System.out.println("[ActiveSessionListener] Session créée. Actives : " + n);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        int n = counter.decrementAndGet();
        se.getSession().getServletContext().setAttribute("activeSessions", n);
        System.out.println("[ActiveSessionListener] Session détruite. Actives : " + n);
    }
}