package LO43;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Scene extends JPanel implements Runnable {

    private boolean pause;

    public ArrayList<Element> elements;

    public Scene(){
        super();
        elements= new ArrayList<Element>();

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
            g.fillRect(e.x, e.y, e.width, e.height);
        }
        g.setColor(Color.white);
    }

    @Override
    public void run() {
        while (true){
            //traitement
            if(!pause) {

                for (Element e : elements) { //pour chaque element
                    if (e.isMovable()) {

                        //verification bord de map X
                        if (e.x + e.getDx() <= 0) {
                            e.setLocation(0, e.y);
                            e.setDx(-e.getDx());//rebond
                        } else if (e.x + e.width + e.getDx() >= getWidth()) {
                            e.setLocation(getWidth() - e.width, e.y);
                            e.setDx(-e.getDx());//rebond
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
                            if (e.getDx()<0) e.setDx(e.getDx()+1);
                            if (e.getDx()>0) e.setDx(e.getDx()-1);

                            e.setLand(e.getDy()==0);
                        } else {
                            //gravité
                            e.setDy(e.getDy() + 1);
                        }

                        //hitbox
                        for (Element other : elements){
                            if (e!=other && new Rectangle(e.x+e.getDx(),e.y,e.width,e.height)
                                            .intersects(other)){
                                // impact horizontal - on echange les directions
                                if (other.isMovable()) {
                                    int tmpDx = new Double(e.getDx() * e.getBounceRate()).intValue();
                                    e.setDx(new Double(other.getDx() * other.getBounceRate()).intValue());
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
                            if (e!=other && new Rectangle(e.x,e.y+e.getDy(),e.width,e.height)
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
                                    if (e.getDx() > other.getDx()) {
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
                                        e.setDy(new Double(-(e.getDy())*e.getBounceRate()).intValue());
                                        //frottements
                                        if (e.getDx()<0) e.setDx(e.getDx()+1);
                                        if (e.getDx()>0) e.setDx(e.getDx()-1);

                                        e.setLand(e.getDy()==0);
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

            try{ // limite framerate nulle en attendant
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean isPaused() {
        return pause;
    }
}
