package fr.iutfbleau.ProjetAgile.Vue.Puissance4;
import java.awt.*;
import fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4;
import fr.iutfbleau.ProjetAgile.Vue.Jeu;


public class Puissance4 extends Jeu {

    private Pion[][] tabPion;
    private Pion[] tabPionVide;
    private int joueur = 1;
    private GridBagLayout layout; 

    /**
     * Crée un Panneau contenant la vue du puissance 4.
     */
    public Puissance4(){ 
        tabPion = new Pion[ConstantesPuissance4.LIGNE][ConstantesPuissance4.COLONNE];
        tabPionVide = new Pion[ConstantesPuissance4.COLONNE];
        GridBagConstraints gbc = new GridBagConstraints();
        this.layout = new GridBagLayout();
        this.setLayout(this.layout);
        this.setOpaque(false);
        for(int i = 0; i < ConstantesPuissance4.COLONNE; i++){
            Pion pion = new Pion(ConstantesPuissance4.EMPTY);
            this.tabPionVide[i] = pion;
            gbc.gridx = i;
            gbc.gridy = 0 ;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.NONE; 
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.weightx = 0; 
            gbc.weighty = 0;  
            gbc.insets  = new Insets(ConstantesPuissance4.INSET,ConstantesPuissance4.INSET,ConstantesPuissance4.INSET,ConstantesPuissance4.INSET);
            this.add(pion,gbc);
        }
        for(int i = 0; i < ConstantesPuissance4.LIGNE; i++){
            for(int j = 0; j < ConstantesPuissance4.COLONNE; j++){
                Pion pion = new Pion(0);
                this.tabPion[i][j] = pion;
                gbc.gridx = j;
                gbc.gridy = i + 1;
                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.fill = GridBagConstraints.NONE; 
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.weightx = 0; 
                gbc.weighty = 0;  
                gbc.insets  = new Insets(ConstantesPuissance4.INSET,ConstantesPuissance4.INSET,ConstantesPuissance4.INSET,ConstantesPuissance4.INSET);
                this.add(pion,gbc);
            }
        }
    }

 
    /**
     * Fonction qui s'éxecute lors du changement du modèle.
     * @param ligne la ligne 
     * @param colonne la colonne
     * @param player le numéro du joueur
     */

    public void grilleChange(int ligne, int colonne, int player) {
        this.tabPion[ligne][colonne].setJoueur(player);
        this.repaint();
    }

    /**
     * Permet de changer le tour du joueur.
     * @param player le joueur qui joue
     */

    public void tourJoueur(int player){
        this.joueur = player;
        this.repaint();
    }

    @Override
    public void clear(){
        for(int i = 0; i < ConstantesPuissance4.LIGNE; i++){
            for(int j = 0; j < ConstantesPuissance4.COLONNE; j++){
                this.tabPion[i][j].setJoueur(ConstantesPuissance4.NO_PLAYER);
            }
        }
        this.tourJoueur(1);
        this.setDessinCol(-1);
    }

    /**
     * Permet de changer la couleur d'un pion au dessus de la grille .
     * @param x
     */
    public void setDessinCol(int x) {
        for(int i = 0; i < ConstantesPuissance4.COLONNE; i++){
            if(x == i )
                this.tabPionVide[i].setJoueur(this.joueur);
            else
                this.tabPionVide[i].setJoueur(ConstantesPuissance4.EMPTY);
        }
        this.repaint();
    }
}

