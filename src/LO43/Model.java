package LO43;


import java.util.ArrayList;

public class Model {

    public static final int VITESSE_DEPLACEMENT = 12, HAUTEUR_SAUT = 20;

    public boolean du, dr, dl, gu, gl, gr;

    public ArrayList<Element> elements;

    public Model(){
        du = false; dr = false; dl = false; gu=false; gl=false; gr = false;
    }

    public void deplacementElement(){
        for (Element element : elements){
            if(element.isControlable()){
                if((element.isControlGauche() && gl) || (element.isControlDroite() && dl)) {
                    if(element.getDx() > -VITESSE_DEPLACEMENT)
                    element.setDx(element.getDx()-4);
                }
                if((element.isControlGauche() && gr) || (element.isControlDroite() && dr)){
                    if(element.getDx() < VITESSE_DEPLACEMENT)
                        element.setDx(element.getDx()+4);
                }

                if (((element.isControlGauche() && gu)|| (element.isControlDroite() && du)) && element.isLand()){
                    element.setDy(-HAUTEUR_SAUT);
                    element.setLand(false);
                }
            }
        }
    }

}
