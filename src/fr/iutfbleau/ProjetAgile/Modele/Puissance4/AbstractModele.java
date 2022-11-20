package fr.iutfbleau.ProjetAgile.Modele.Puissance4;
import javax.swing.event.EventListenerList;

import fr.iutfbleau.ProjetAgile.Listener.Puissance4.GrilleEvent;
import fr.iutfbleau.ProjetAgile.Listener.Puissance4.GrilleListenerInterface;
import fr.iutfbleau.ProjetAgile.Listener.Puissance4.JoueurEvent;

/**
 * Classe qui permet de créer les méthodes pour la grille, afin, d'éviter de surcharger la classe Modeèle
 */
public class AbstractModele {
    
    /**
     * Crée une liste de EventListener contenant tous les listeners de la classe. 
     */
    private final EventListenerList listeners = new EventListenerList();

    /**
     * Ajoute un nouveau listener à la liste de listener.
     * @param listener un listener de la classe.
     */
    public void addGrilleListener(GrilleListenerInterface listener){
        listeners.add(GrilleListenerInterface.class, listener);
    }
 
     
    /**
     * Supprime un listener à la liste de listener.
     * @param listener un listener de la classe
     */
    public void removeGrilleListener(GrilleListenerInterface listener) {
        listeners.remove(GrilleListenerInterface.class, listener);
    }
 
     
    /**
     * Retourne tous les listeners de la classe.
     * @return Retourne tous les listeners de la classe
     */
    public GrilleListenerInterface[] getGrilleListeners() {
        return listeners.getListeners(GrilleListenerInterface.class);
    }

     
    /**
     * Notifie aux écouteur que la grille a changée.
     * @param ligne la ligne qui a changée 
     * @param col la colonne qui a changée
     * @param player le joueur qui joue
     */
    protected void fireGrilleChanged(int ligne, int col, int player) {
        GrilleEvent event = null;
        for(GrilleListenerInterface listener : getGrilleListeners()) {
            if(event == null)
                event = new GrilleEvent(ligne, col, player);
            listener.GrilleChange(event);
        }
    }

    
    
    /**
     * Notifie aux écouteur que le tour du joueur a changée.
     * @param joueur le numéro du joueur
     * @param nom le nom du joueur
     */
    protected void fireJoueur(int joueur, String nom) {
        JoueurEvent event = null;
        for(GrilleListenerInterface listener : getGrilleListeners()) {
            if(event == null)
                event = new JoueurEvent(joueur, nom, 0);
            listener.tourJoueur(event);
        }
    }

    /**
     * Notifie aux écouteur que le joueur a gagné.
     * @param joueur le numéro du joueur
     * @param nom le nom du joueur
     * @param score le score du joueur
     */

    protected void fireGagne(int joueur, String nom, int score ) {
        JoueurEvent event = null;
        for(GrilleListenerInterface listener : getGrilleListeners()) {
            if(event == null)
                event = new JoueurEvent(joueur, nom, score);
            listener.Gagne(event);
        }
    }

    /**
     * Notifie aux écouteur qu'il y a égalité.
     */
    protected void fireDraw() {
        for(GrilleListenerInterface listener : getGrilleListeners()) {
            listener.draw();
        }
    }


}
