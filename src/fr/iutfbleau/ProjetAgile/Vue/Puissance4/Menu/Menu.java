package fr.iutfbleau.ProjetAgile.Vue.Puissance4.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4;
import fr.iutfbleau.ProjetAgile.Vue.Boutons;

/**
 * Classe représentant le menu du Mini-jeux.
 */

public class Menu extends JPanel {


    public Menu(MouseListener listener){
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        JLabel titre = new JLabel("Mini-Jeux");
        titre.setFont(new Font("SansSerif", Font.BOLD, 30));
        this.setBackground(Color.decode("#C0C0C0"));
        gbc.gridx = 0;
        gbc.gridy = 0 ;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.5; 
        gbc.weighty = 0.8;  
        this.add(titre,gbc);
        // Bouton accéder au menu du choix du jeu.
        gbc.gridx = 0;
        gbc.gridy = 1 ;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;; 
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weightx = 0.5; 
        gbc.weighty = 0.1;   
        gbc.insets  = new Insets(10,0,20,0);
        Boutons play = new Boutons(ConstantesPuissance4.JOUER);
        play.addMouseListener(listener);
        this.add(play,gbc);
        


        // Bouton pour quitter le Menu.
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weightx = 0; 
        gbc.weighty = 0.1;  
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE; 
        Boutons quit = new Boutons(ConstantesPuissance4.QUITTER);
        quit.addMouseListener(listener);
        this.add(quit,gbc);
    }
  
}