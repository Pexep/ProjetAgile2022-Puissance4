package fr.iutfbleau.ProjetAgile.Vue;
import javax.swing.*;

import fr.iutfbleau.ProjetAgile.Listener.BoutonListener;
import fr.iutfbleau.ProjetAgile.Vue.Puissance4.Menu.ChoixJeu;
import fr.iutfbleau.ProjetAgile.Vue.Puissance4.Menu.Menu;

import java.awt.*;


public class JFrameJeu extends JFrame {
    
    private CardLayout layout;
    private PanelJeu panelJeu;

    /**
     * Construit une fenêtre de jeux.
     */
    public JFrameJeu(){
        super();
        this.setMinimumSize(new Dimension(600,600));
        Container container = this.getContentPane();
        layout = new CardLayout();
        container.setLayout(layout); 

        BoutonListener listener  = new BoutonListener(this);
        
        container.add(new Menu(listener));
        container.add(new ChoixJeu(listener));
        panelJeu = new PanelJeu(listener);
        container.add(panelJeu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Permets de naviguer dans le gestionnaire de page.
     */
    public void nextPage(){
        this.layout.next(this.getContentPane());
    }
    
    /**
     * Permets de naviguer dans le gestionnaire de page.
     */
    public void previousPage(){
        this.layout.previous(this.getContentPane());
    }

    /**
     * Retourne le panel du jeu.
     * @return Retourne le panel du jeu
     */
    public PanelJeu getPanelJeu() {
        return panelJeu;
    }

    /**
     * Permets de fermer la page.
     */
    public void exit(){
        this.dispose();
    }
}

