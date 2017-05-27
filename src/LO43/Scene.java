package LO43;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kiéran on 27/05/2017.
 */
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
                        e.setLocation(e.x, getHeight() - e.height);
                        e.setDy(-e.getDy());
                    } else {
                        //gravité
                        e.setDy(e.getDy()+1);
                    }

                    //déplacement
                    e.setLocation(e.x+e.getDx(), e.y+e.getDy());

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
