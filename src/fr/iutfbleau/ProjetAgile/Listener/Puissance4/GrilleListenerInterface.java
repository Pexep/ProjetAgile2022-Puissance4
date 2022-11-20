package fr.iutfbleau.ProjetAgile.Listener.Puissance4;

import java.util.EventListener;

public interface GrilleListenerInterface extends EventListener{
    /**
     * Notifié un changement du modèle. 
     * @param e un GrilleEvent contenant les informations nécessaire au changement
     */
    void GrilleChange(GrilleEvent e);

    /**
     * Notifié que le modèle a détecté un gagnant.
     * @param e un JoueurEvent contenant les informations du gagnant 
     */
    void Gagne(JoueurEvent e);

     /**
     * Notifié que joueur a changé.
     * @param e un JoueurEvent contenant les informations du gagnant 
     */
    void tourJoueur(JoueurEvent e);

    /**
     * Notifié d'une égalité.
     */
    void draw();
}
