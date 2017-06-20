package LO43;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Scene extends JPanel implements Runnable {

    private boolean pause;
    private Model model;

    public ArrayList<Element> elements;

    public Scene(Model m){
        super();
        elements= new ArrayList<Element>();
        model= m;
        model.elements = elements;

        m.lireSauvegarde("default");
    }

    public void setPause(boolean p){
        pause=p;
    }

    public void addElement(Element e){
        elements.add(e);
    }

    protected void paintComponent(Graphics g){
        g.setColor(Color.white);

        super.paintComponent(g);

        for (Element e : elements){
            g.setColor(e.getColor());
            if(e instanceof ZoneActivable){
                g.drawRect(e.x, e.y, e.width, e.height);
                if (((ZoneActivable) e).isActivated()) {
                    g.drawRect(e.x - 1, e.y - 1, e.width + 2, e.height + 2);
                    g.drawRect(e.x + 1, e.y + 1, e.width - 2, e.height - 2);
                }
            }
            else g.fillRect(e.x, e.y, e.width, e.height);
        }
        g.setColor(Color.white);
    }

    @Override
    public void run() {
        while (true){
            //traitement
            if(!pause) {

                model.deplacementElement();

                for (Element e : elements) { //pour chaque element
                    if (e.isMovable()) {

                        //verification bord de map X
                        if (e.x + e.getDx() <= 0) {
                            e.setLocation(0, e.y);
                            e.setDx(- new Double(e.getDx()*e.getBounceRate()).intValue());//rebond
                        } else if (e.x + e.width + e.getDx() >= getWidth()) {
                            e.setLocation(getWidth() - e.width, e.y);
                            e.setDx(- new Double(e.getDx()*e.getBounceRate()).intValue());//rebond
                        }

                        //verification bord de map Y
                        if (e.y + e.getDy() <= 0) {
                            e.setLocation(e.x, 0);
                            e.setDy(-e.getDy());
                        } else if (e.y + e.height + e.getDy() >= getHeight()) {
                            //bas de la map
                            e.setLocation(e.x, getHeight() - e.height);
                            e.setDy(new Double(-(e.getDy())*e.getBounceRate()).intValue());
                            //frottements
                            /*if (e.getDx()<0) e.setDx(e.getDx()+1);
                            if (e.getDx()>0) e.setDx(e.getDx()-1);*/
                            e.setDx(new Double(e.getDx()*e.getFrictionRate()).intValue());

                            e.setLand(e.getDy()==0);
                        } else {
                            //gravité
                            e.setDy(e.getDy() + 1);
                        }

                        //hitbox
                        for (Element other : elements){
                            if (other instanceof ZoneActivable){
                                if (e!=other && e.intersects(other)) ((ZoneActivable) other).activer(e);
                                continue;
                            }
                            if (e!=other && e.getNextRectangle(Element.X_axis)
                                            .intersects(other)){
                                // impact horizontal - on echange les directions
                                if (other.isMovable()) {
                                    int tmpDx = new Double(e.getDx() * (e.isControlable()? 0.8 : e.getBounceRate())).intValue();
                                    e.setDx(new Double(other.getDx() * (other.isControlable() ? 0.8 : other.getBounceRate())).intValue());
                                    other.setDx(tmpDx);
                                }else{ // other = element fixe
                                    if (e.x + e.getDx() <= other.x + other.width && e.x + e.getDx() > other.x) {
                                        e.setLocation(other.x + other.width, e.y);
                                        e.setDx(new Double(-e.getDx()*e.getBounceRate()).intValue());//rebond
                                    } else if (e.x + e.width + e.getDx() >= other.x) {
                                        e.setLocation(other.x - e.width, e.y);
                                        e.setDx(new Double(-e.getDx()*e.getBounceRate()).intValue());//rebond
                                    }
                                }
                            }
                            if (e!=other && e.getNextRectangle(Element.Y_axis)
                                            .intersects(other)){
                                // impact vertical - on echange les directions
                                if (other.isMovable()) {

                                    int tmpDy = new Double(e.getDy() * e.getBounceRate()).intValue();
                                    e.setDy(new Double(other.getDy() * other.getBounceRate()).intValue());
                                    other.setDy(tmpDy);

                                    //frottements
                                    if (e.getDx() < other.getDx()) {
                                        e.setDx(e.getDx() + 1);
                                        other.setDx(other.getDx() - 1);
                                    }
                                    if (e.getDx() > other.getDx() && (e.getDx() != 0 && other.getDx()!=-1)) {
                                        e.setDx(e.getDx() - 1);
                                        other.setDx(other.getDx() + 1);
                                    }

                                    // atterissage?
                                    e.setLand(e.getDy() == 0);
                                    other.setLand(other.getDy() == 0);
                                }else{ // other = element fixe
                                    if (e.y + e.getDy() <= other.y + other.height && e.y + e.getDy() > other.y) {
                                        e.setLocation(e.x, other.y+other.height);
                                        e.setDy(new Double(-e.getDy()*e.getBounceRate()).intValue());
                                    } else if (e.y + e.height + e.getDy() >= other.y) {

                                        e.setLocation(e.x, other.y - e.height);
                                        e.setDy(- new Double(e.getDy()*e.getBounceRate()).intValue());
                                        //frottements
                                        /*if (e.getDx()<0) e.setDx(e.getDx()+1);
                                        if (e.getDx()>0) e.setDx(e.getDx()-1);*/
                                        e.setDx(new Double(e.getDx()*e.getFrictionRate()).intValue());

                                        e.setLand(e.getDy()==0);
                                    }
                                }
                            }
                            if (other.isMovable() && e!=other && e.intersects(other)){ // en cas de superposition
                                double dx = e.getCenterX() - other.getCenterX();
                                double dy = e.getCenterY() - other.getCenterY();

                                if (Math.abs(dy) > Math.abs(dx)){ // deplacement vertical
                                    if (dy <0) e.setLocation(e.x, e.y -1);
                                    else other.setLocation(other.x, other.y -1);
                                }else{
                                    if (dx <0){
                                        e.setLocation(e.x -1, e.y);
                                        other.setLocation(other.x +1, other.y);
                                    }else{

                                        e.setLocation(e.x +1, e.y);
                                        other.setLocation(other.x -1, other.y);
                                    }
                                }
                            }
                        }

                        //déplacement
                        e.setLocation(e.x + e.getDx(), e.y + e.getDy());
                    }
                }
            }
            repaint();

            try{ // limite framerate
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Element e : elements){
                if (e instanceof ZoneActivable){
                    if(((ZoneActivable) e).isActivated()) {
                        ((ZoneActivable) e).desactiver();
                    }

                }
            }
        }
    }

    public boolean isPaused() {
        return pause;
    }
}
