package fr.iutfbleau.ProjetAgile.Listener;

import java.awt.event.*;
import javax.swing.JOptionPane;
import fr.iutfbleau.ProjetAgile.Vue.Boutons;
import fr.iutfbleau.ProjetAgile.Vue.JFrameJeu;
import fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4;
import fr.iutfbleau.ProjetAgile.Controleur.ControleurInterface;
import fr.iutfbleau.ProjetAgile.Controleur.Puissance4.ControleurPuissance4;
import java.awt.Cursor;

public class BoutonListener extends MouseAdapter{

    private JFrameJeu fenetre;
    private ControleurInterface controleur;

    /**
     * Construit un BoutonListener avec une fênetre spécifique.
     * @param f la fênetre.
     */
    public BoutonListener(JFrameJeu f){
        this.fenetre = f;
    }


    public void mouseClicked(MouseEvent e) {
        Boutons Boutons = (Boutons) e.getComponent();
        String command = Boutons.getText();
        switch (command) {
            case ConstantesPuissance4.JOUER:
                fenetre.nextPage();
                break;

            case ConstantesPuissance4.QUITTER:
                fenetre.exit();
                break;
        
            case ConstantesPuissance4.PUISSANCE:
                this.controleur = new ControleurPuissance4(fenetre);
                this.controleur.lancer();
                break;

            case ConstantesPuissance4.REJOUER:
                this.controleur.clear();
                break;

            case ConstantesPuissance4.ACCUEIL:
                fenetre.nextPage();
                break;

            case ConstantesPuissance4.RETOUR:
                fenetre.previousPage();
                break;

            default:
                JOptionPane.showMessageDialog(fenetre, "Le jeu est indisponible", "Indisponible", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
        Boutons Boutons = (Boutons) e.getComponent();
        Boutons.setHover(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Boutons Boutons = (Boutons) e.getComponent();
        Boutons.setHover(false);
    }
}

