package fr.iutfbleau.ProjetAgile.Listener.Puissance4;

public class JoueurEvent {

    private int joueur;
    private String nom;
    private int score;

    /**
     * Construit un GrilleListener avec le numéro et le nom spécifié.
     * @param joueur le numéro du joueur 
     * @param nom le nom du joueur 
     * @param score le score actuel du joueur
     */
    public JoueurEvent(int joueur, String nom, int score){
        this.joueur = joueur;
        this.nom = nom;
        this.score = score;
    }

    /**
     * Retourne le numéro du joueur.
     * @return le numéro du joueur
     */
    public int getJoueur() {
        return joueur;
    }

    /**
     * Retourne le nom du joueur.
     * @return le nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le score du joueur.
     * @return le score du joueur
     */
    public int getScore() {
        return score;
    }
}
