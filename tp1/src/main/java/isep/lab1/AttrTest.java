package isep.lab1;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/AttrTest")
public class AttrTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String scope = request.getParameter("scope");   // session | app | request
        String action = request.getParameter("action"); // add | replace | remove

        HttpSession session = request.getSession();
        ServletContext ctx = getServletContext();

        if ("session".equals(scope)) {
            if ("add".equals(action)) session.setAttribute("user", "ali");
            if ("replace".equals(action)) session.setAttribute("user", "sami");
            if ("remove".equals(action)) session.removeAttribute("user");
        } else if ("app".equals(scope)) {
            if ("add".equals(action)) ctx.setAttribute("counter", 1);
            if ("replace".equals(action)) ctx.setAttribute("counter", 2);
            if ("remove".equals(action)) ctx.removeAttribute("counter");
        } else if ("request".equals(scope)) {
            if ("add".equals(action)) request.setAttribute("msg", "hello");
            if ("replace".equals(action)) {
                request.setAttribute("msg", "hello");
                request.setAttribute("msg", "bonjour");
            }
            if ("remove".equals(action)) {
                request.setAttribute("msg", "hello");
                request.removeAttribute("msg");
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<html><body><h2>Test attributs</h2>"
                + "<p>Dernier test : " + scope + " / " + action + " (voir la console)</p>"
                + "<p>Session: <a href='AttrTest?scope=session&action=add'>add</a> | "
                + "<a href='AttrTest?scope=session&action=replace'>replace</a> | "
                + "<a href='AttrTest?scope=session&action=remove'>remove</a></p>"
                + "<p>Application: <a href='AttrTest?scope=app&action=add'>add</a> | "
                + "<a href='AttrTest?scope=app&action=replace'>replace</a> | "
                + "<a href='AttrTest?scope=app&action=remove'>remove</a></p>"
                + "<p>Request: <a href='AttrTest?scope=request&action=add'>add</a> | "
                + "<a href='AttrTest?scope=request&action=replace'>replace</a> | "
                + "<a href='AttrTest?scope=request&action=remove'>remove</a></p>"
                + "</body></html>");
    }
}