package fr.iutfbleau.ProjetAgile.Modele.Puissance4;

import java.util.HashMap;
import java.util.LinkedList;

import fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4;

public class Modele extends AbstractModele{

    private enum Direction {HORIZONTAL,VERTICAL,DIAG_LEFT_RIGHT,DIAG_RIGHT_LEFT}

    
    private int joueur ;
    private HashMap<Integer,String> nom = new HashMap<>();
    private HashMap<Integer,Integer> score = new HashMap<>();
    private Integer[][] tab;
    private boolean play = true; 
    private int nbrJoueur;
    private int numCoup = 0;
    private LinkedList<Integer> joueurs = new LinkedList<Integer>();

    /**
     * Créer un modele avec les constantes {@link fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4#LIGNE Ligne} et {@link fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4#COLONNE Colonne}.
     * @param nbrJoueur Nombre de joueurs.
     */
    public Modele(int nbrJoueur){
        this.nbrJoueur = nbrJoueur;
        this.joueur = ConstantesPuissance4.PLAYER_1;
        this.tab = new Integer[ConstantesPuissance4.LIGNE][ConstantesPuissance4.COLONNE];
        for(int i = 1; i <= nbrJoueur; i++){
            this.score.put(i,0);
            this.joueurs.add(i);
        }
        for(int i = 0; i < ConstantesPuissance4.LIGNE; i++){
            for(int j = 0; j < ConstantesPuissance4.COLONNE; j++){
                tab[i][j] = 0;
            }
        }
    }

    /**
     * Permets d'instancier des joueurs.
     * @param joueur1 Premier joueur.
     * @param joueur2 Deuxième joueur.
     * @param joueur3 Troisieme joueur.
     */
    public void setJoueurs(String joueur1, String joueur2,String joueur3){
        nom.put(1,joueur1);
        nom.put(2,joueur2);
        nom.put(3,joueur3);
    }

    /**
     * Retourne le joueur qui peut jouer.
     * Utilisé par le controleur pour modifié le numéro tour du joueur 
     * @return le joueur qui peut jouer 
     */
    public int getJoueur() {
        return joueur;
    }

    /**
     * Retourne le nom  joueur qui peut jouer.
     * Utilisé par le controleur pour modifié le nom du joueur 
     * @return le nom joueur qui peut jouer 
     */
    public String getNom() {
        return nom.get(this.joueur);
    }
    
    /**
     * Retourne le score du joueur passé en paramètre.
     * Utilisé par le controleur pour affiché le score au début
     * Sera utile lors de l'ajout de la possibilité de reprendre une partie 
     * @param joueur le joueur voulu
     * @return le score du joueur
     */
    public int getScore(int joueur){
        return score.get(joueur);
    }   

    /**
     * Arrête le jeu.
     */
    private void stop() {

        this.play = false;
    }

    /**
     * Permet de savoir si le jeu est en cours. 
     * @return True si on joue False sinon
     */
    public boolean isPlaying() {
        return this.play == true;
    }

    /**
     * Permet de changer le jour qui joue. 
     */
    public void switchPlayer(){
        int index = this.joueurs.indexOf((Integer)joueur);
        if(index == this.joueurs.size()-1)
        {
            this.joueur = this.joueurs.getFirst();
        } else {
            this.joueur = this.joueurs.get(index + 1);
        }
    }

    /**
     * Permet de vider la grille.
     */
    public void clear(){
        this.play = true;
        this.joueur = 1;
        for(int i = 0; i < ConstantesPuissance4.LIGNE; i++){
            for(int j = 0; j < ConstantesPuissance4.COLONNE; j++){
                tab[i][j] = 0;
            }
        }
        this.joueurs.clear();
        for(int i = 1; i <= nbrJoueur; i++)
        {
            this.joueurs.add(i);
        }
        this.numCoup = 0;
    }

    /**
     * Permet de jouer.
     * @param col la colonne dans laquelle jouer 
     */
    public void joue(int col) {
        for(int i = 5; i >= 0; i -- ){
            if(tab[i][col] == 0){
                tab[i][col] = joueur;
                this.fireGrilleChanged(i, col, joueur);
                this.numCoup += 1 ;
                if(detectGagnant(i,col)){
                    score.put(joueur, score.get(joueur) + 1);
                    this.fireGagne(joueur,getNom(), getScore(joueur));
                    if(this.joueurs.size() == 3){
                        vidange();
                        int joueur = this.joueur; // on sauvegarde le joueur pour remove le bon apres (on ne peut pas remove avant le switch sinon sa casse tout).
                        this.switchPlayer();
                        this.joueurs.remove((Integer)joueur);
                        whoWin();
                        this.fireJoueur(this.joueur, getNom());
                    } else {
                        this.stop();
                    }
                }
                else if(this.numCoup >= (ConstantesPuissance4.LIGNE * ConstantesPuissance4.COLONNE)){
                    this.stop();
                    this.fireDraw();
                }
                else{
                    this.switchPlayer();
                    this.fireJoueur(joueur,getNom());
                }    
                return ;
            }
        }
        return;
    }

    /**
     * Vérifie si il y a une combinaison gagnante.
     * 
     * @param x indice de la ligne du pion à l'origine de la vérifiation
     * @param y indice de la colonne du pion à l'origine de la vérification
     * @return true s'il y a une combinaison gagnante, false dans le cas contraire
     */
    public boolean detectGagnant(int x, int y){
        int nbrGagnant = this.joueurs.size() == 2 ? 4 : 3;
        if (this.detectGagnant(x,y,0,Direction.HORIZONTAL,1,joueur, nbrGagnant)>0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Vérifie s'il y a une combinaison gagnante.
     * 
     * @param x indice ligne du pion à vérifier.
     * @param y indice colonne du pion à vérifier.
     * @param nb le nombre de pions d'un même joueur sur la ligne.
     * @param direction la direction de la ligne vérifiée.
     * @param sens le sens de parcours de la ligne à partir du pion d'origine.
     * @param tour le joueur qui a posé le pion.
     * @param nbrGagnant nombre de pions alignés pour gagner.
     * @return le nombre de pions d'un même joueur sur une ligne (max 7)
     */
    private int detectGagnant(int x, int y, int nb, Direction direction, int sens, int tour, int nbrGagnant){

        if (nb == 4 | x>=ConstantesPuissance4.LIGNE | y>=ConstantesPuissance4.COLONNE | y<0 | x<0){
            return nb;
        }else if (this.tab[x][y]==0 | this.tab[x][y]!=tour){
            return nb;
        }else{
            if (nb==0){
                int gagnant1 = detectGagnant(x+1, y, nb+1, Direction.VERTICAL,1,tour,nbrGagnant)+detectGagnant(x-1, y, nb+1, Direction.VERTICAL,-1,tour, nbrGagnant)-1;
                int gagnant2 = detectGagnant(x, y+1, nb+1, Direction.HORIZONTAL,1,tour, nbrGagnant)+detectGagnant(x, y-1, nb+1, Direction.HORIZONTAL,-1,tour, nbrGagnant)-1;
                int gagnant3 = detectGagnant(x+1, y+1, nb+1, Direction.DIAG_LEFT_RIGHT,1,tour, nbrGagnant)+detectGagnant(x-1, y-1, nb+1, Direction.DIAG_LEFT_RIGHT,-1,tour, nbrGagnant)-1;
                int gagnant4 = detectGagnant(x-1, y+1, nb+1, Direction.DIAG_RIGHT_LEFT,1,tour, nbrGagnant)+detectGagnant(x+1, y-1, nb+1, Direction.DIAG_RIGHT_LEFT,-1,tour, nbrGagnant)-1;
                if (gagnant1>=nbrGagnant | gagnant2>=nbrGagnant | gagnant3>=nbrGagnant | gagnant4>=nbrGagnant){
                    return 1;
                }else{
                    return 0;
                }
            }
            int x1 = x, y1 = y;
            switch (direction) {
                case VERTICAL:
                    x1+=sens;
                    break;

                case HORIZONTAL:
                    y1+=sens;
                    break;

                case DIAG_LEFT_RIGHT:
                    x1+=sens;
                    y1+=sens;
                    break;

                default:
                    x1-=sens;
                    y1+=sens;
                    break;
            }
            return detectGagnant(x1, y1, nb+1, direction,sens,tour, nbrGagnant);
        }
    }


    /**
     * fonction qui vérifie tous les pions du joueur qui joue.
     * @return retourne true si le joueur a gagné
     */
    public boolean fullVerif(){
        for(int i = ConstantesPuissance4.LIGNE - 1; i >= 0; i--){
            for(int j = 0 ; j < ConstantesPuissance4.COLONNE; j++){
                if(detectGagnant(i, j)){
                    return true;
                }
            }
        }
        return false;
    }

     /**
     * fonction qui vérifie qui a gagné.
     * Si les 2 joueurs ont gagné le jeu est notifié de sa égalité 
     * Si un 1 joueur a gagné le jeu est notifié de sa victoire 
     * Sinon il n'y a rien 
     */
    public void whoWin(){
        boolean joueurWin = fullVerif();
        switchPlayer();
        boolean joueur2Win = fullVerif();
        if( joueurWin && joueur2Win){
            this.fireDraw();
            stop();
        }
        else if( joueurWin || joueur2Win){
            if(joueurWin)
                switchPlayer();
            score.put(joueur, score.get(joueur) + 1);
            this.fireGagne(joueur, getNom(), getScore(joueur));
            stop();
        }
        switchPlayer();
    }

    /**
     * Permet de remettre faire passer le jeu de 3 à 2 joueurs.
     */
    private void vidange()
    {
        for(int i = 0; i < ConstantesPuissance4.LIGNE; i++){
            for(int j = 0; j < ConstantesPuissance4.COLONNE; j++){
                if(tab[i][j] == joueur)
                {
                    tab[i][j] = 0;
                    this.fireGrilleChanged(i, j, 0);
                }     
            }
        }
        int indice = 0;
        for(int k = 0; k < ConstantesPuissance4.COLONNE; k++){
            indice = ConstantesPuissance4.LIGNE - 1;
            for(int l = ConstantesPuissance4.LIGNE-1; l >= 0; l--){
                if(tab[l][k] != 0){
                    int joueur = tab[l][k];
                    tab[l][k] = 0;
                    this.fireGrilleChanged(l, k, 0);
                    tab[indice][k] = joueur;
                    this.fireGrilleChanged(indice, k, joueur);
                    indice--;
                }
            }
        }
    }
}