package fr.iutfbleau.ProjetAgile.Listener.Puissance4;

/**
 * Classe représentant un GrilleEvent.
 */
public class GrilleEvent {
    private int ligne;
    private int col;
    private int player;
 
    /**
     * Construit un GrilleEvent avec la ligne, la colonne et le joueur.
     * @param ligne La ligne de la Grille 
     * @param col La colonne de la Grille
     * @param player Le joueur qui a joué
     */
    public GrilleEvent(int ligne, int col,int player) {
        this.ligne = ligne;
        this.col = col;
        this.player = player;
    }
 
    /**
     * Retourne le numéro de la ligne.
     * @return Retourne le numéro de la ligne
     */
    public int getLigne() {
        return ligne;
    }
 
    /**
     * Retourne le numéro de la colonne.
     * @return Retourne le numéro de la colonne
     */
    public int getCol() {
        return col;
    }

    /**
     * Retourne le joueur.
     * @return Retourne le joueur 
     */
    public int getPlayer() {
        return player;
    }
}
