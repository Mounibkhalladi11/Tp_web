package isep.lab1.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent e) {
        System.out.println("[Application] ajouté : " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent e) {
        System.out.println("[Application] remplacé : " + e.getName()
                + " (ancienne valeur = " + e.getValue() + ")");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent e) {
        System.out.println("[Application] supprimé : " + e.getName());
    }
}