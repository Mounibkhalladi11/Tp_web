package isep.lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet("/ShowSession")
public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // Nombre de sessions actives (mis à jour par ActiveSessionListener)
        Object active = getServletContext().getAttribute("activeSessions");

        String fullName = (String) session.getAttribute("fullName");
        String birth = (String) session.getAttribute("birth");

        // Premier accès : afficher le formulaire
        if (fullName == null || birth == null) {
            out.println("<html><body><h2>Welcome on my site</h2>");
            out.println("<p>Sessions actives : " + active + "</p>");
            out.println("<form method='POST' action='ShowSession'>");
            out.println("Nom complet : <input type='text' name='fullName'><br><br>");
            out.println("Date de naissance : <input type='date' name='birth'><br><br>");
            out.println("<input type='submit' value='Envoyer'>");
            out.println("</form></body></html>");
            return;
        }

        // Utilisateur de retour
        Integer visits = (Integer) session.getAttribute("visits");
        visits = (visits == null) ? 1 : visits + 1;
        session.setAttribute("visits", visits);

        LocalDate birthDate = LocalDate.parse(birth); // format yyyy-MM-dd
        LocalDate today = LocalDate.now();
        LocalDate next = birthDate.withYear(today.getYear());
        if (next.isBefore(today)) {
            next = next.plusYears(1);
        }
        long days = ChronoUnit.DAYS.between(today, next);

        out.println("<html><body><h2>Welcome back</h2>");
        out.println("<p>Hi, " + fullName + ". There are " + days
                + " days to your birthday.</p>");
        out.println("<p>Nombre de visites : " + visits + "</p>");
        out.println("<p>Sessions actives : " + active + "</p>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("fullName", request.getParameter("fullName"));
        session.setAttribute("birth", request.getParameter("birth"));
        session.setAttribute("visits", 1);

        // Redirection vers GET pour afficher le message
        response.sendRedirect("ShowSession");
    }
}