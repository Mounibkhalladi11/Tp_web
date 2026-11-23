package isep.lab1.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

@WebServlet("/ListItems")
public class ListItems extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        if (items == null) {
            items = new ArrayList<>();
            session.setAttribute("items", items);
        }

        String newItem = request.getParameter("newItem");
        if (newItem != null && !newItem.trim().isEmpty()) {
            items.add(newItem.trim());
        }

        show(items, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        show(items == null ? new ArrayList<>() : items, response);
    }

    private void show(ArrayList<String> items, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Liste des items</h2><ul>");
        for (String item : items) {
            out.println("<li>" + item + "</li>");
        }
        out.println("</ul>");

        // Extra : nombre de fois que chaque item a été saisi
        out.println("<h3>Occurrences</h3><ul>");
        for (String item : new LinkedHashSet<>(items)) {
            out.println("<li>" + item + " : " + Collections.frequency(items, item) + "</li>");
        }
        out.println("</ul><a href='items.html'>Ajouter un item</a></body></html>");
    }
}