package fr.iutfbleau.ProjetAgile.Vue;

import java.awt.Graphics;
import javax.swing.JComponent;

import fr.iutfbleau.ProjetAgile.Constant.ConstantesPuissance4;

import java.awt.*;

public class Boutons extends JComponent{

    private String nom;
    private String chemin;
    private boolean hover;

    /**
     * Initialise un bouton avec un nom et un nom d'image
     * @param nom le nom du bouton 
     * @param chemin le nom de l'image (chemin)
     */
    public Boutons(String nom, String chemin){
        super();
        this.setFont(ConstantesPuissance4.FONT);
        this.hover = false;
        this.setPreferredSize(new Dimension(300,200));
        this.setMinimumSize(new Dimension(200,150));
        this.nom = nom;
        this.chemin = chemin;
    }

    public Boutons(String nom ){
        super();
        this.setFont(ConstantesPuissance4.FONT);
        this.hover = false;
        this.setPreferredSize(new Dimension(250,45));
        this.setMinimumSize(new Dimension(150,25));
        this.nom = nom;
    }
    
    /*
     * Pour que le listener des boutons connaisse le nom 
     */
    public String getText(){
        return nom;
    }

    public boolean isHover(){
        return this.hover;
    }

    public void setHover(boolean e){
        this.hover = e;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gg2d = (Graphics2D) g.create();
        gg2d.setColor(Color.decode("#094d1e"));
        Color text = Color.WHITE;
        FontMetrics metrics = gg2d.getFontMetrics(gg2d.getFont());
        gg2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 0;
        int img = 0;
        // Si le Component est survolé 
        if(this.isHover()){
            x = 2;
            img = 5;   
            text = ConstantesPuissance4.PLAYER_1_COLOR;

        }
        // Si le chemin vers une image n'est pas null
        if(this.chemin != null){
            Image img1;
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try{
                img1 =  Toolkit.getDefaultToolkit().getImage(loader.getResource("res/Img/"+ this.chemin + ".png"));
                gg2d.drawImage(img1,img,img,this.getWidth() - img, this.getHeight() - img, this);
            }  catch(Exception e){ 
                e.printStackTrace();
                return;
            }
        }
        else{
            gg2d.fillRoundRect(x,x, this.getWidth(), this.getHeight(),5,5);
            gg2d.setColor(Color.black);
            gg2d.setStroke(new BasicStroke((float) ConstantesPuissance4.STROKE));
            gg2d.drawLine(x,x,x, this.getHeight() - ConstantesPuissance4.STROKE);
            gg2d.drawLine(x,this.getHeight() - 1, this.getWidth() , this.getHeight() - 1);
        }
        gg2d.setColor(text);
        gg2d.drawString(this.nom, (this.getWidth() - metrics.stringWidth(this.nom) )/ 2, (this.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent() + x );
    }
}

