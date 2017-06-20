package LO43;

import org.w3c.dom.Document;
import java.awt.*;


public class Element extends Rectangle {
    public static final int X_axis = 1, Y_axis = 2;
    protected double BOUNCE_RATE = 0.5;
    protected double FRICTION_RATE = 0.8;
    protected int dx, dy; //deplacement
    protected Color color;
    protected boolean movable;
    protected boolean land;
    protected boolean controlGauche, controlDroite;

    protected String nomType;
    public String nom;

    public Element(String n, int x, int y, int w, int h, int dx, int dy, Color c){
        super(x,y,w,h);
        this.dx = dx;
        this.dy = dy;
        nom = n;
        color = c;
        movable = true;
        nomType = "Element";

        land = false;
        controlDroite = false;
        controlGauche = false;
    }

    public Element(org.w3c.dom.Element xml){
        this(   xml.getAttribute("nom"),
                Integer.parseInt(xml.getAttribute("x")), Integer.parseInt(xml.getAttribute("y")),
                Integer.parseInt(xml.getAttribute("width")), Integer.parseInt(xml.getAttribute("height")),
                Integer.parseInt(xml.getAttribute("dx")), Integer.parseInt(xml.getAttribute("dy")),
                new Color(Integer.parseInt(xml.getAttribute("color"))));
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

        return elemXML;
    }

    public Color getColor() {
        return color;
    }

    public boolean isMovable(){return movable;}

    public Rectangle getNextRectangle(int axe){
        if(axe == X_axis)return new Rectangle(x+dx, y, width, height);
        if(axe == Y_axis)return new Rectangle(x, y+dy, width, height);
        return new Rectangle(x+dx, y+dy, width, height);

    }


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
    public boolean isControlable() { return controlDroite || controlGauche;}

    public final double getBounceRate(){
        return BOUNCE_RATE;
    }

    public double getFrictionRate() {
        return FRICTION_RATE;
    }

    public String toString(){
        return nom;
    }
}
