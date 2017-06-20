package LO43;

import org.w3c.dom.Document;

import java.awt.*;
import java.util.ArrayList;

/**
 * Auteur : Kiéran le 09/06/2017.
 */
public class ZoneActivable extends Element {
    public static final int ARRIVEE = 0;
    private int effet;
    private Element trig;
    private boolean alreadyActivated;

    public ZoneActivable(String n, int x, int y, int w, int h, Color c, int effet, Element trigger) {
        super(n, x, y, w, h, 0, 0, c);
        this.effet = effet;
        trig = trigger;
        movable = false;
        nomType = "ZoneActivable";
        alreadyActivated = false;
    }

    public ZoneActivable(org.w3c.dom.Element xml, ArrayList<Element> elements){
        this(   xml.getAttribute("nom"),
                Integer.parseInt(xml.getAttribute("x")), Integer.parseInt(xml.getAttribute("y")),
                Integer.parseInt(xml.getAttribute("width")), Integer.parseInt(xml.getAttribute("height")),
                new Color(Integer.parseInt(xml.getAttribute("color"))), Integer.parseInt(xml.getAttribute("effet")), null);

        String nomTrigger = xml.getAttribute("nomTrigger");
        if(nomTrigger.equals("")) return;
        for (Element e : elements){
            if (nomTrigger.equals(e.toString())){
                trig = e;
                break;
            }
        }
    }

    public org.w3c.dom.Element getXML(Document doc){
        org.w3c.dom.Element elemXML = doc.createElement(nomType);
        elemXML.setAttribute("nom", nom);
        elemXML.setAttribute("nomTrigger", trig == null ? "" : trig.toString());
        elemXML.setAttribute("x", String.valueOf(x));
        elemXML.setAttribute("y", String.valueOf(y));
        elemXML.setAttribute("width", String.valueOf(width));
        elemXML.setAttribute("height", String.valueOf(height));
        elemXML.setAttribute("color", String.valueOf(color.getRGB()));
        elemXML.setAttribute("effet", String.valueOf(effet));

        return elemXML;
    }

    public void activer(Element e){
        if (alreadyActivated || (trig!= null && e!=trig)) return;
        alreadyActivated = true;
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
