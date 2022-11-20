package fr.iutfbleau.ProjetAgile.Constant;
import java.awt.Color;
import java.awt.Font;

/**
 * Classe qui représente les Constantes pour le jeu Puissance 4.
 */
public class ConstantesPuissance4 {

    /**
     * Le nombre de ligne.
     */
    public static final int LIGNE = 6;

    /**
     * Le nombre de colonne.
     */
    public static final int COLONNE = 7;


    /**
     * La font utilisé pour les textes.
     */
    public static final Font FONT = new Font("SansSerif", Font.BOLD, 20);

    /**
     * La taille de la ligne pour les boutons.
     */
    public static final int STROKE = 2;

     /**
     * Constante qui représente la couleur du joueur 1.
     */
    public static final Color PLAYER_1_COLOR = Color.decode("#FFC947");

     /**
     * Constante qui représente la couleur du joueur 2.
     */
    public static final Color PLAYER_2_COLOR = Color.decode("#F32424");

    /**
     * Constante qui représente  la couleur du joueur 3.
     */
    public static final Color PLAYER_3_COLOR = Color.decode("#FF00FF");

    /**
     * Constante qui représente le joueur 1.
     */
    public static final int PLAYER_1 = 1;

    /**
     * Constante qui représente le joueur 2.
     */
    public static final int PLAYER_2 = 2;

    /**
     * Constante qui représente le joueur 3.
     */
    public static final int PLAYER_3 = 3;
    
    /**
     * Constante qui représente le fait que les cases sont "invisible".
     */
    public static final int EMPTY = -1;

    /**
     * Constante qui représente une case vide.
     */
    public static final int NO_PLAYER = 0;

    /**
     * Constante pour les insets entre les pion.
     */
    public static final int INSET = 5;

    
    //Constantes pour les boutons
     
    /**
     * Constante pour les jeux indisponibles.
     */
    public static final String INDISPO = "Indisponible";

    /**
     * Constante pour le bouton Jouer.
     */
    public static final String JOUER = "Jouer";

    /**
     * Constante pour le bouton Quitter.
     */
    public static final String QUITTER = "Quitter";

    /**
     * Constante pour le bouton jeu Puissance 4.
     */
    public static final String PUISSANCE = "Puissance 4";

    /**
     * Constante pour le bouton Retour.
     */
    public static final String RETOUR = "Retour";

    /**
     * Constante pour le bouton Rejouer.
     */
    public static final String REJOUER = "Rejouer";

    /**
     * Constante pour le bouton Accueil.
     */
    public static final String ACCUEIL = "Accueil";
}
