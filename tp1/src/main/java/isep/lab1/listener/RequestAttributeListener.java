package isep.lab1.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent e) {
        System.out.println("[Request] ajouté : " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent e) {
        System.out.println("[Request] remplacé : " + e.getName()
                + " (ancienne valeur = " + e.getValue() + ")");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent e) {
        System.out.println("[Request] supprimé : " + e.getName());
    }
}