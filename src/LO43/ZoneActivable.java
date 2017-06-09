package LO43;

import java.awt.*;

/**
 * Auteur : Kiéran le 09/06/2017.
 */
public class ZoneActivable extends Element {
    public static final int ARRIVEE = 0;
    private int effet;
    private Element trig;

    public ZoneActivable(int x, int y, int w, int h, int dx, int dy, Color c, int effet, Element trigger) {
        super(x, y, w, h, dx, dy, c);
        this.effet = effet;
        trig = trigger;
        movable = false;
    }

    public void activer(Element e){
        if (trig!= null && e!=trig) return;
        switch (effet){
            case ARRIVEE :
                System.out.println("arrivee");
                // Do Smthg
                break;

            default:
                System.out.println("sans effet");
        }
    }


}
