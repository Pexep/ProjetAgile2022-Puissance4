package fr.iutfbleau.ProjetAgile.Vue.Puissance4;

import javax.swing.*;

import fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4;

import java.awt.*;

/**
 * Une classe qui représente un Pion.
 */

public class Pion extends JComponent {

    private int joueur;

    public Pion(int joueur ){
        this.joueur = joueur;
        this.setPreferredSize(new Dimension(100,100));
        this.setMinimumSize(new Dimension(50,50));
        this.setSize(new Dimension(50,50));
    }
    
    /**
     * Permet de set le joueur du pion.
     * @param joueur Joueur voulue.
     */
    public void setJoueur(int joueur){
        this.joueur = joueur;
    }

    /**
     * Redessine le composant.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics gg = g.create();
        Graphics2D gg2d = (Graphics2D)gg;
        gg2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        switch(joueur){
            case ConstantesPuissance4.EMPTY : // Pour la premiere
                gg2d.setColor(Color.decode("#185ADB"));
                break;
            case ConstantesPuissance4.NO_PLAYER:
                gg2d.setColor(Color.decode("#150485"));
                break;
            case ConstantesPuissance4.PLAYER_1:
                gg2d.setColor(ConstantesPuissance4.PLAYER_1_COLOR);
                break;
            case ConstantesPuissance4.PLAYER_2:
                gg2d.setColor(ConstantesPuissance4.PLAYER_2_COLOR);
                break;
            case ConstantesPuissance4.PLAYER_3:
                gg2d.setColor(ConstantesPuissance4.PLAYER_3_COLOR);
                break;
        }
        gg2d.fillOval(0, 0, gg2d.getClipBounds().width, gg2d.getClipBounds().height);
    }
}

