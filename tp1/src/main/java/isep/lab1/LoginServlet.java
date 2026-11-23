package isep.lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");

        HttpSession session = request.getSession();
        session.setAttribute("user", username);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<html><body><h2>Connecté : " + username + "</h2>"
                + "<p><a href='items.html'>Aller à items.html</a></p>"
                + "<p><a href='LogoutServlet'>Se déconnecter</a></p></body></html>");
    }
}