package isep.lab1.servlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDay = request.getParameter("birthDay");

        response.addCookie(new Cookie("firstName", firstName.trim().replace(" ", "_")));
        response.addCookie(new Cookie("lastName", lastName.trim().replace(" ", "_")));
        response.addCookie(new Cookie("birthDay", birthDay.trim().replace("/", "-")));

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("""         
                <html>
                <body>
                    <h2>Informations recues</h2>
                    <table border='1'>
                        <tr><th>First name</th><td>%s</td></tr>
                        <tr><th>Last name</th><td>%s</td></tr>
                        <tr><th>Birth date</th><td>%s</td></tr>
                    </table>
                </body>
                </html>""".formatted(firstName, lastName, birthDay));
    }
}