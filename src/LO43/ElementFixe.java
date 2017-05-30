package LO43;

import java.awt.*;

/**
 * Auteur : Kiéran le 30/05/2017.
 */
public class ElementFixe extends Element {
    public ElementFixe(int x, int y, int w, int h, Color c) {
        super(x, y, w, h, 0, 0, c);
        movable = false;
    }
}
