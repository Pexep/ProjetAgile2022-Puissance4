package fr.iutfbleau.ProjetAgile.Controleur.Puissance4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.swing.*;
import fr.iutfbleau.ProjetAgile.Vue.JFrameJeu;
import fr.iutfbleau.ProjetAgile.Vue.Puissance4.Puissance4;
import fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4;
import fr.iutfbleau.ProjetAgile.Controleur.ControleurInterface;
import fr.iutfbleau.ProjetAgile.Listener.Puissance4.GrilleEvent;
import fr.iutfbleau.ProjetAgile.Listener.Puissance4.GrilleListener;
import fr.iutfbleau.ProjetAgile.Listener.Puissance4.GrilleListenerInterface;
import fr.iutfbleau.ProjetAgile.Listener.Puissance4.JoueurEvent;
import fr.iutfbleau.ProjetAgile.Listener.Puissance4.ListenerPuissance4;
import fr.iutfbleau.ProjetAgile.Modele.Puissance4.Modele;

/**
 * Une classe qui implémente {@link fr.iutfbleau.ProjetAgile.Controleur.ControleurInterface ControleurInterface}.<br>
 * Cette classe est le controleur du jeu Puissance 4
 */

public class ControleurPuissance4 implements ControleurInterface{
    
    private Modele modele;
    private Puissance4 vue;
    private JFrameJeu fenetre;

    /**
     * Le controleur du jeu.
     * @param fenetre la {@link fr.iutfbleau.ProjetAgile.Vue.JFrameJeu fenetre} principale
     */
    public ControleurPuissance4(JFrameJeu fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void lancer(){

        String[] options = {"3 joueurs", "2 joueurs", "annuler"};

        int result = JOptionPane.showOptionDialog(this.fenetre, "Choisir un nombre de joueurs","nombre de joueurs",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(result == 0){
            connexion("3_joueur");
            JTextField field1 = new JTextField("Joueur 1");
            JTextField field2 = new JTextField("Joueur 2");
            JTextField field3 = new JTextField("Joueur 3");
            Object[] message = {
                "Entrez le nom du joueur 1", field1,
                "Entrez le nom du joueur 2", field2,
                "Entrez le nom du joueur 3", field3,
            };
            int choix = JOptionPane.showConfirmDialog(this.fenetre, message, "Entrez les noms des joueurs", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (choix == JOptionPane.OK_OPTION){
                try {
                    if(field1.getText().equals("") || field2.getText().equals(""))
                        throw new IllegalArgumentException();
                    if(field1.getText().length() > 10 || field2.getText().length() > 10 )
                        throw new StringIndexOutOfBoundsException();
                    String joueur1 = field1.getText();
                    String joueur2 = field2.getText();
                    String joueur3 = field3.getText();
                    this.modele = new Modele(3);
                    this.modele.setJoueurs(joueur1, joueur2,joueur3);
                    this.fenetre.getPanelJeu().setScore(joueur1,modele.getScore(ConstantesPuissance4.PLAYER_1),joueur2, modele.getScore(ConstantesPuissance4.PLAYER_2), joueur3, modele.getScore(ConstantesPuissance4.PLAYER_3));
                    this.fenetre.getPanelJeu().changeText(1, joueur1);
                }
                catch (StringIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(this.fenetre.getPanelJeu(),"Un des trois noms est trop grand","Erreur ", JOptionPane.ERROR_MESSAGE);
                    this.lancer();
                }catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this.fenetre.getPanelJeu(),"Un des trois noms est vide","Erreur ", JOptionPane.ERROR_MESSAGE);
                    this.lancer();
                }
            }
            else{
                return;
            }
        }else if (result == 1) {
            connexion("2_joueur");
            JTextField field1 = new JTextField("Joueur 1");
            JTextField field2 = new JTextField("Joueur 2");
            Object[] message = {
                "Entrez le nom du joueur 1", field1,
                "Entrez le nom du joueur 2", field2,
            };
            int choix = JOptionPane.showConfirmDialog(this.fenetre, message, "Entrez les noms des joueurs", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (choix == JOptionPane.OK_OPTION){
                try {
                    if(field1.getText().equals("") || field2.getText().equals(""))
                        throw new IllegalArgumentException();
                    if(field1.getText().length() > 10 || field2.getText().length() > 10 )
                        throw new StringIndexOutOfBoundsException();
                    String joueur1 = field1.getText();
                    String joueur2 = field2.getText();
                    this.modele = new Modele(2);
                    this.modele.setJoueurs(joueur1, joueur2, null);
                    this.fenetre.getPanelJeu().setScore(joueur1,modele.getScore(ConstantesPuissance4.PLAYER_1),joueur2, modele.getScore(ConstantesPuissance4.PLAYER_2));
                    this.fenetre.getPanelJeu().changeText(1, joueur1);
                }
                catch (StringIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(this.fenetre,"Un des deux noms est trop grand","Erreur ", JOptionPane.ERROR_MESSAGE);
                    this.lancer();
                }catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this.fenetre,"Un des deux noms est vide","Erreur ", JOptionPane.ERROR_MESSAGE);
                    this.lancer();
                }
            }
            else{
                return;
            }
        }  
        else{
            return;
        }  
        this.vue = new Puissance4();
        ListenerPuissance4 listener = new ListenerPuissance4(this);
        GrilleListenerInterface grilleListener = new GrilleListener(this);
        this.modele.addGrilleListener(grilleListener);            
        this.vue.addMouseListener(listener);
        this.vue.addMouseMotionListener(listener);
        this.fenetre.getPanelJeu().setJeu(this.vue);      
        this.fenetre.nextPage();
    }
    
    
    /**
     * Permet d'actualisé la base de donnée.
     * @param colonne la colonne de la table à actualisé 
     */
    public void connexion(String colonne){
        try{
           Class.forName("org.mariadb.jdbc.Driver");
           Connection cnx = DriverManager.getConnection("link","login","pwd");
           try{
                String format = MessageFormat.format("Update Puissance set {0} = {0}  + 1", colonne);
                PreparedStatement pst = cnx.prepareStatement(format);
                pst.setString(1, colonne);
                pst.setString(2, colonne);
                pst.executeUpdate();
                pst.close();
                cnx.close();
           }
           catch(SQLException e){
               System.out.println("Erreur dans le prepare Statement");
               cnx.close();
           }
       }
       catch( ClassNotFoundException  e){
           System.out.println("Il n'y a pas de pilote disponible");
       }
       catch(SQLException e2){
           System.out.println("La connexion n'a pas abouti");
       }
   }

    @Override
    public void clear(){
        this.modele.clear();
        this.vue.clear();
        this.fenetre.getPanelJeu().changeText(this.modele.getJoueur(), this.modele.getNom());    
    }

    @Override
    public void gagne(JoueurEvent e){
        this.fenetre.getPanelJeu().gagne(e.getJoueur(), e.getNom(), e.getScore());
    }

    /**
     * Notifie à la vue que le modèle a changé .
     * @param e un {@link fr.iutfbleau.ProjetAgile.Listener.Puissance4.GrilleEvent GrilleEvent} qui contient la ligne, la colonne et le numéro du joueur
     */
    public void grilleChange(GrilleEvent e){
        this.vue.grilleChange(e.getLigne(), e.getCol(), e.getPlayer());
    }

    /**
     * Notifie à au {@link fr.iutfbleau.ProjetAgile.Vue.PanelJeu PanelJeu} qu'il y a une égalité 
    */
    public void draw(){
        this.fenetre.getPanelJeu().draw();
    }

    /**
     * Retourne si le jeu est en cours.
     * @return Retourne si le jeu est en cours
     */
    public boolean isPlaying(){
        return this.modele.isPlaying();
    }

    /**
     * Retourne la largeur de la vue.
     * @return Retourne la largeur de la vue
     */
    public int getWidthVue(){
        return this.vue.getWidth();
    }

    /**
     * Notifie au {@link fr.iutfbleau.ProjetAgile.Modele.Puissance4.Modele modèle} qu'un utilisateur a cliqué dans une colonne de la vue.
     * @param colonne la colonne dans laquelle jouer 
     */
    public void joue(int colonne){
        this.modele.joue(colonne);
    }

     /**
     * Permet de dessiner un pion au dessus de la colonne où la souris se situe.
     * @param colonne la colonne dans laquelle dessiner
     */
    public void setDessinCol(int colonne){
        this.vue.setDessinCol(colonne);
    }

    /**
     * Notifie à la {@link fr.iutfbleau.ProjetAgile.Vue.Puissance4.Puissance4  vue}et au {@link fr.iutfbleau.ProjetAgile.Vue.PanelJeu PanelJeu} que le tour du joueur a changé .
     * @param e  un JoueurEvent qui contient les informations du joueur
     */
    public void tourJoueur(JoueurEvent e) {
        this.vue.tourJoueur(e.getJoueur());
        this.fenetre.getPanelJeu().changeText(e.getJoueur(), e.getNom());
    }

  
}