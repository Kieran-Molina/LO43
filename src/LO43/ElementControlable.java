package LO43;

import java.awt.*;

/**
 * Auteur : Kiéran le 30/05/2017.
 */
public class ElementControlable extends Element {
    public ElementControlable(int x, int y, int w, int h, int dx, int dy, Color c, boolean gauche) {
        super(x, y, w, h, dx, dy, c);
        controlGauche = gauche;
        controlDroite = !gauche;

        BOUNCE_RATE = 0.4;
    }
}
