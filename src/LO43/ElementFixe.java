package LO43;

import org.w3c.dom.Document;

import java.awt.*;

/**
 * Auteur : Kiéran le 30/05/2017.
 */
public class ElementFixe extends Element {
    public ElementFixe(String n, int x, int y, int w, int h, Color c) {
        super(n, x, y, w, h, 0, 0, c);
        nomType = "ElementFixe";
        movable = false;
        BOUNCE_RATE = 0;
    }

    public ElementFixe(org.w3c.dom.Element xml){
        this(   xml.getAttribute("nom"),
                Integer.parseInt(xml.getAttribute("x")), Integer.parseInt(xml.getAttribute("y")),
                Integer.parseInt(xml.getAttribute("width")), Integer.parseInt(xml.getAttribute("height")),
                new Color(Integer.parseInt(xml.getAttribute("color"))));
    }

    public org.w3c.dom.Element getXML(Document doc){
        org.w3c.dom.Element elemXML = doc.createElement(nomType);
        elemXML.setAttribute("nom", nom);
        elemXML.setAttribute("x", String.valueOf(x));
        elemXML.setAttribute("y", String.valueOf(y));
        elemXML.setAttribute("width", String.valueOf(width));
        elemXML.setAttribute("height", String.valueOf(height));
        elemXML.setAttribute("color", String.valueOf(color.getRGB()));

        return elemXML;
    }
}
