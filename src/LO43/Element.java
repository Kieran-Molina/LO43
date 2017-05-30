package LO43;

import java.awt.*;


public class Element extends Rectangle {
    protected double BOUNCE_RATE = 0.8;
    protected int dx, dy; //deplacement
    protected Color color;
    protected boolean movable;
    protected boolean land;
    protected boolean controlGauche, controlDroite;

    public Element(int x, int y, int w, int h, int dx, int dy, Color c){
        super(x,y,w,h);
        this.dx = dx;
        this.dy = dy;
        color = c;
        movable = true;

        land = false;
        controlDroite = false;
        controlGauche = false;

    }

    public Color getColor() {
        return color;
    }

    public boolean isMovable(){return movable;}


    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public boolean isLand() {
        return land;
    }

    public void setLand(boolean land) {
        this.land = land;
    }

    public boolean isControlDroite() {
        return controlDroite;
    }
    public boolean isControlGauche() {
        return controlGauche;
    }

    public final double getBounceRate(){
        return BOUNCE_RATE;
    }
}
