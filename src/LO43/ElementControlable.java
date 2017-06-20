package LO43;

import org.w3c.dom.Document;

import java.awt.*;

/**
 * Auteur : Kiéran le 30/05/2017.
 */
public class ElementControlable extends Element {
    public ElementControlable(String n, int x, int y, int w, int h, int dx, int dy, Color c, boolean gauche) {
        super(n, x, y, w, h, dx, dy, c);
        nomType = "ElementControlable";
        controlGauche = gauche;
        controlDroite = !gauche;

        BOUNCE_RATE = 0.4;
    }

    public ElementControlable(org.w3c.dom.Element xml){
        this(   xml.getAttribute("nom"),
                Integer.parseInt(xml.getAttribute("x")), Integer.parseInt(xml.getAttribute("y")),
                Integer.parseInt(xml.getAttribute("width")), Integer.parseInt(xml.getAttribute("height")),
                Integer.parseInt(xml.getAttribute("dx")), Integer.parseInt(xml.getAttribute("dy")),
                new Color(Integer.parseInt(xml.getAttribute("color"))), Boolean.parseBoolean(xml.getAttribute("gauche")));
    }

    public org.w3c.dom.Element getXML(Document doc){
        org.w3c.dom.Element elemXML = doc.createElement(nomType);
        elemXML.setAttribute("nom", nom);
        elemXML.setAttribute("x", String.valueOf(x));
        elemXML.setAttribute("y", String.valueOf(y));
        elemXML.setAttribute("width", String.valueOf(width));
        elemXML.setAttribute("height", String.valueOf(height));
        elemXML.setAttribute("dx", String.valueOf(dx));
        elemXML.setAttribute("dy", String.valueOf(dy));
        elemXML.setAttribute("color", String.valueOf(color.getRGB()));
        elemXML.setAttribute("gauche", String.valueOf(controlGauche));

        return elemXML;
    }
}
