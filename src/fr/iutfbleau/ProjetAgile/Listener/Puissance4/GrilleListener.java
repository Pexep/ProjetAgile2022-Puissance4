package fr.iutfbleau.ProjetAgile.Listener.Puissance4;

import fr.iutfbleau.ProjetAgile.Controleur.Puissance4.ControleurPuissance4;
/**
 * Implémente l'interface GrilleListenerInterface.
 */
public class GrilleListener implements GrilleListenerInterface{

    private ControleurPuissance4 controleur;

    /**
     * Construit un GrilleListener avec le controleur specifié. 
     * @param controleur Le controleur 
     */
    public GrilleListener(ControleurPuissance4 controleur){
        this.controleur = controleur;
    }
    
    @Override
    public void GrilleChange(GrilleEvent e) {
        controleur.grilleChange(e);
    }

    @Override
    public void Gagne(JoueurEvent e) {
        controleur.gagne(e);
    }

    @Override
    public void tourJoueur(JoueurEvent e) {
        controleur.tourJoueur(e);
    }
    
    @Override
    public void draw(){
        controleur.draw();
    }
}
