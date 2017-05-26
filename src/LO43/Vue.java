package LO43;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kiéran on 26/05/2017.
 */
public class Vue extends JFrame{

    private Model model;

    public JButton bLancer, bStopper, bAjouter; // ....

    public Vue(Model m){

        model = m;

        setSize(1000,700);
        creerAffichage();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("LO43 - Molina Kieran / Yucel Vincent");

        setVisible(true);
    }

    private void creerAffichage() {
        JPanel tout = new JPanel();
        tout.setLayout(new BoxLayout(tout,BoxLayout.X_AXIS));

        JPanel barreLat = new JPanel();
        barreLat.setLayout(new GridLayout(15,1,20,20));
        barreLat.setMaximumSize(new Dimension(150, 700));

        bLancer = new JButton("lancer");
        bStopper = new JButton("pause");

        bAjouter = new JButton("ajouter");

        barreLat.add(bLancer);
        barreLat.add(bStopper);
        barreLat.add(bAjouter);


        JPanel scene = new JPanel();
        scene.setSize(850,700);



        tout.add(barreLat);
        tout.add(scene);

        setContentPane(tout);
    }

    public void setControlMenu(ControlMenu controlMenu) {
        //ici, ajouter le controleur à tous les éléments du menu
        // element.addActionListener(controlMenu);
    }

    public void setControlButon(ControlButton controlButton) {
        //ici, ajouter le controleur à tous les boutons cliquables
        // element.addActionListener(controlButton);
        bLancer.addActionListener(controlButton);
        bStopper.addActionListener(controlButton);
        bAjouter.addActionListener(controlButton);
    }
}
