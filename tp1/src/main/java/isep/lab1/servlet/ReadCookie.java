package isep.lab1.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ReadCookie")

public class ReadCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("""
                <html>
                <body>
                    <h2>Cookies</h2>
                    <table border='1'>""");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("firstName") || c.getName().equals("lastName") || c.getName().equals("birthDay")) {
                    out.println("""
                                <tr>
                                    <th>%s</th>
                                    <td>%s</td>
                                </tr>""".formatted(c.getName(), c.getValue()));
                }
            }
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}