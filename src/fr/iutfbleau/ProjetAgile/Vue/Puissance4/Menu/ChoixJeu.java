package fr.iutfbleau.ProjetAgile.Vue.Puissance4.Menu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4;
import fr.iutfbleau.ProjetAgile.Vue.Boutons;

public class ChoixJeu extends JPanel  {

  /**
   * Construit un Panel qui contient tous les choix de jeux avec comme paramètre le listener des boutons.
   * @param listener Le listener des boutons 
   */
  public ChoixJeu(MouseListener listener){

    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    this.setLayout(gridbag);
    this.setBackground(Color.decode("#C0C0C0"));

    ScrollableJPanel scrollPanel = new ScrollableJPanel();
    scrollPanel.setLayout(gridbag);
    scrollPanel.setBackground(Color.decode("#A9A9A9"));
    JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scroll.setViewportView(scrollPanel);
    scroll.getViewport().setBackground(Color.decode("#A9A9A9"));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(10,10,10,10);
    gbc.weightx = 0.2; 
    gbc.weighty = 0.2;
    gbc.fill = GridBagConstraints.NONE ;
    Boutons puissance4 = new Boutons(ConstantesPuissance4.PUISSANCE, "puissance4");
    puissance4.addMouseListener(listener);

    scrollPanel.add(puissance4, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;

    gbc.fill = GridBagConstraints.NONE;
    Boutons demineur = new Boutons(ConstantesPuissance4.INDISPO,"demineur");
    demineur.addMouseListener(listener);

    scrollPanel.add(demineur, gbc);

    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    
    gbc.fill = GridBagConstraints.NONE;
    Boutons t = new Boutons(ConstantesPuissance4.INDISPO, "morpion");
    t.addMouseListener(listener);
    scrollPanel.add(t, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    
    gbc.fill = GridBagConstraints.NONE;
      t = new Boutons(ConstantesPuissance4.INDISPO, "morpion");
    t.addMouseListener(listener);
    scrollPanel.add(t, gbc);

    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.weightx = 1; 
    gbc.weighty = 0.8;
    gbc.fill = GridBagConstraints.BOTH;
    this.add(scroll,gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.weightx = 0; 
    gbc.weighty = 0.2;
    gbc.fill = GridBagConstraints.NONE;
    gbc.insets = new Insets(10,0,0,0);
    Boutons quit = new Boutons(ConstantesPuissance4.RETOUR);
    quit.addMouseListener(listener); 
    this.add(quit,gbc);

  }
}
