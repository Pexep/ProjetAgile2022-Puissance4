package fr.iutfbleau.ProjetAgile.Controleur;

import fr.iutfbleau.ProjetAgile.Listener.Puissance4.JoueurEvent;

/**
 * Une Interface qui représente un controleur de jeu.
 * On pourra rajouté une méthode permettant de lancer un jeu qui a déjà commencé
 */
public interface ControleurInterface {

    /**
     * Permet de remettre à zéro le jeu et la vue 
     */
    public void clear();

    /**
     * Permet de changer la vue avec le joueur gagnant 
     * @param e un {@link fr.iutfbleau.ProjetAgile.Listener.Puissance4.JoueurEvent JoueurEvent} qui représente le joueur gagnant
     */
    public void gagne(JoueurEvent e);
    
    /**
     * Permet de lancer le jeu et la vue 
     */
    public void lancer();
}
