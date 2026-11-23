package lab.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.mvc.model.Person;

import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String birthDay  = request.getParameter("birthDay"); // mm/dd/yyyy
        String sex       = request.getParameter("sex");

        Person person = new Person(firstName, lastName, birthDay, sex);
        request.setAttribute("person", person);

        // Extraction de l'année (dernier élément après "/")
        int year = 0;
        try {
            year = Integer.parseInt(birthDay.substring(birthDay.lastIndexOf('/') + 1).trim());
        } catch (Exception e) {
            // date invalide : on traite comme "young"
            year = 9999;
        }

        String page = (year < 1990) ? "/old.jsp" : "/young.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.include(request, response);
    }
}