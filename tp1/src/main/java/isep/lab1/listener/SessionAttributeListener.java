package isep.lab1.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent e) {
        System.out.println("[Session] ajouté : " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent e) {
        // getValue() retourne l'ANCIENNE valeur
        System.out.println("[Session] remplacé : " + e.getName()
                + " (ancienne valeur = " + e.getValue() + ")");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent e) {
        System.out.println("[Session] supprimé : " + e.getName() + " = " + e.getValue());
    }
}