package fr.iutfbleau.ProjetAgile.Vue;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.*;

public class PanelJeu extends JPanel{
    
    private Jeu jeu ;
    private GridBagConstraints gbc = new GridBagConstraints(); 
    private JLabel tourJoueur = new JLabel();
    private JLabel scoreJoueur1 = new JLabel();
    private JLabel scoreJoueur2 = new JLabel();
    private JLabel scoreJoueur3= new JLabel();

    /**
     * Construit un panneau contenant des boutons et un jeu. 
     * @param listener Le listener des boutons 
     */
    public PanelJeu(MouseListener listener){
        super();
        this.setBackground(Color.decode("#185ADB"));
        this.setLayout(new GridBagLayout());
        Boutons button1 = new Boutons(ConstantesPuissance4.ACCUEIL);
        button1.addMouseListener(listener);
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.5;
        gbc.weighty = 0; 
        this.add(button1,gbc);

        Boutons button2 = new Boutons(ConstantesPuissance4.REJOUER);
        button2.addMouseListener(listener);
        gbc.gridx = 1; 
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.5;
        gbc.weighty = 0; 
        this.add(button2,gbc); 
          
        JPanel panelScore = new JPanel();
        this.scoreJoueur1.setFont(ConstantesPuissance4.FONT);
        this.scoreJoueur2.setFont(ConstantesPuissance4.FONT);
        this.scoreJoueur3.setFont(ConstantesPuissance4.FONT);
        panelScore.setBackground(getBackground());
        panelScore.setLayout(new FlowLayout());
        panelScore.add(scoreJoueur1);
        panelScore.add(scoreJoueur2);
        panelScore.add(scoreJoueur3);
        gbc.gridx = 0;
        gbc.gridy = 1 ;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.weightx = 0; 
        gbc.weighty = 0; 
        this.add(panelScore,gbc);

        this.tourJoueur.setFont(ConstantesPuissance4.FONT);
        gbc.gridx = 0;
        gbc.gridy = 3 ;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.weightx = 0; 
        gbc.weighty = 0; 
        gbc.insets = new Insets(10,0,0,0);
       
        this.add(tourJoueur,gbc); 
        this.setVisible(true);
    }

    /**
     * Permet de changer le jeu contenu dans le panneau.
     * @param jeu le jeu voulu
     */
    public void setJeu(Jeu jeu){
        if(this.jeu == null){
            this.jeu = jeu;
            gbc.gridwidth = 2;
            gbc.gridx = 0; 
            gbc.gridy = 2;  
            this.add(jeu,gbc);  
            this.repaint();
        }
        else{
            this.remove(this.jeu);
            this.jeu = jeu;
            gbc.gridwidth = 2;
            gbc.gridx = 0; 
            gbc.gridy = 2;  
            this.add(jeu,gbc); 
            this.repaint();
            this.revalidate();
        }
    }   

    /**
     * Permet d'initialisé le score d'une partie avec 2 joueurs.
     * @param joueur1 le numéro du joueur1
     * @param score1 le nom du joueur1
     * @param joueur2 le numéro du joueur2
     * @param score2 le nom du joueur2
     */
    public void setScore(String joueur1, int score1, String joueur2, int score2){
        this.scoreJoueur1.setText(joueur1 + " " + score1);
        this.scoreJoueur2.setText(": " + score2 + " " + joueur2);
        this.scoreJoueur3.setText("");
    }

    /**
     * Permet d'initialisé le score d'une partie avec 3 joueurs.
     * @param joueur1 le numéro du joueur 1
     * @param score1 le nom du joueur 1
     * @param joueur2 le numéro du joueur 2 
     * @param score2 le nom du joueur 2 
     * @param joueur3 le numéro du joueur 3
     * @param score3 le nom du joueur 3
     */
    public void setScore(String joueur1, int score1, String joueur2, int score2,String joueur3, int score3){
        this.scoreJoueur1.setText(joueur1 + " : " + score1 + " | ");
        this.scoreJoueur2.setText(joueur2 + " : " + score2 + " | " );
        this.scoreJoueur3.setText(joueur3 + " : " + score3);
    }
    

     /**
     * Permet d'initialisé le score d'une partie avec 1 joueur.
     * @param joueur le numéro du joueur
     * @param score le nom du joueur
     */
    public void setScore(String joueur, int score){
        this.scoreJoueur1.setText(joueur + " " + score);
    }
    

    /**
     * Notifie que joueur a gagné.
     * @param joueur le numéro du joueur
     * @param nom le nom du joueur
     * @param score le score du joueur
     */
    public void gagne(int joueur, String nom, int score ){
        this.tourJoueur.setText("Bravo " + nom + " ( " + joueur  + " )");
        JOptionPane.showMessageDialog(this, "Bravo tu as gagné " + nom, "Victoire", JOptionPane.INFORMATION_MESSAGE);
        if(this.scoreJoueur3.getText() == ""){
            switch (joueur) {
                case ConstantesPuissance4.PLAYER_1:
                    this.scoreJoueur1.setText(nom + " " + score);
                    break;
            
                default:
                    this.scoreJoueur2.setText( ": " + score + " " + nom);
                    break;
            }
        }
        else{
            switch (joueur) {
                case ConstantesPuissance4.PLAYER_1:
                    this.scoreJoueur1.setText(nom + " : " + score + " | ");
                    break;
            
                case ConstantesPuissance4.PLAYER_2:
                    this.scoreJoueur2.setText(nom + " : " + score + " | ");
                    break;
            
                default:
                    this.scoreJoueur3.setText(nom + " : " + score);
                    break;
            }
        }
        this.repaint();
    }


    /**
     * Notifie l'égalité.
     */
    public void draw(){
        this.tourJoueur.setText("Dommage égalité");
        this.repaint();
    }
    
    /**
     * Notifie que joueur a changé.
     * @param joueur le numéro du joueur
     * @param nom le nom du joueur
     */
    public void changeText(int joueur, String nom){
        Color color = ConstantesPuissance4.PLAYER_1_COLOR;
        switch (joueur) {
            case ConstantesPuissance4.PLAYER_1:
                color = ConstantesPuissance4.PLAYER_1_COLOR;
                break;
            case ConstantesPuissance4.PLAYER_2:
                color = ConstantesPuissance4.PLAYER_2_COLOR;
                break;
            case ConstantesPuissance4.PLAYER_3:
                color = ConstantesPuissance4.PLAYER_3_COLOR;
                break;
        }
        this.tourJoueur.setForeground(color);
        this.tourJoueur.setText(nom + " ( Joueur " + joueur  + " )");
        this.repaint();
    }
}

