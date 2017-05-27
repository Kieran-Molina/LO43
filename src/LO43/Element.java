package LO43;

import java.awt.*;

/**
 * Created by Kiéran on 27/05/2017.
 */
public class Element extends Rectangle {
    private int dx, dy; //deplacement
    private Color color;
    private boolean movable;

    public Element(int x, int y, int w, int h, int dx, int dy, Color c){
        super(x,y,w,h);
        this.dx = dx;
        this.dy = dy;
        color = c;
        movable = true;

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
}
