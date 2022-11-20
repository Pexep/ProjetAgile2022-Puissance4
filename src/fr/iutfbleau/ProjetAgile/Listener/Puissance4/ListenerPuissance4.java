package fr.iutfbleau.ProjetAgile.Listener.Puissance4;
import fr.iutfbleau.ProjetAgile.Controleur.Puissance4.ControleurPuissance4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ListenerPuissance4 extends MouseAdapter {

    private ControleurPuissance4 controleur;

    /**
     * Construit un Listener du jeu Puissance 4 avec comme paramètre un controleur.
     * @param controleur le contrôleur 
     */
    public ListenerPuissance4(ControleurPuissance4 controleur){
        this.controleur = controleur;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(controleur.isPlaying()){
            int x = (e.getX() * 7) / controleur.getWidthVue();
            controleur.joue(x);
            if(this.controleur.isPlaying())
                this.controleur.setDessinCol(x);
            else
                this.controleur.setDessinCol(-1);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(controleur.isPlaying()){
            int x = (e.getX() * 7) / controleur.getWidthVue();
            this.controleur.setDessinCol(x);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.controleur.setDessinCol(-1);
    }
}
