package LO43;

import javax.swing.*;
import java.awt.*;


public class Vue extends JFrame{

    private Model model;

    public JButton bPause, bAjouter; // ....

    public Scene scene;

    public Vue(Model m){

        model = m;

        setSize(1400,900);
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
        barreLat.setMaximumSize(new Dimension(150, 800));

        bPause = new JButton("pause");

        bAjouter = new JButton("ajouter");

        barreLat.add(bPause);
        barreLat.add(bAjouter);


        scene = new Scene(model);
        model.elements = scene.elements;
        scene.setSize(1250,900);

        // TESSTTTSSS

        Element e1 = new Element(50,50,40,40,5,15,Color.red);
        Element e2 = new Element(200,700,60,60,0,0,Color.blue);
        Element ef = new ElementFixe(300,800,150,10, Color.black);
        Element e3 = new ElementControlable(500,400,50,50,-8,-7,Color.green, true);
        Element e4 = new ElementControlable(400,400,50,50,-18,-7,Color.orange, false);
        Element e5 = new ZoneActivable(200,700,60,60,0,0,Color.green, ZoneActivable.ARRIVEE,e3);
        scene.addElement(ef);
        scene.addElement(e1);
        scene.addElement(e2);
        scene.addElement(e3);
        scene.addElement(e4);
        scene.addElement(e5);


        Thread thread = new Thread(scene);
        thread.start();

        //


        tout.add(barreLat);
        tout.add(scene);

        setContentPane(tout);
    }

    public void ajouterElement(){
        //au clic bAjouter
        AjoutElement ajoutElement = new AjoutElement();

        try {
            int result = JOptionPane.showConfirmDialog(null, ajoutElement,
                    "Parametre du nouvel element", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (ajoutElement.movable.isSelected()) {
                    scene.addElement(new Element(Integer.parseInt(ajoutElement.xField.getText()),
                            Integer.parseInt(ajoutElement.yField.getText()),
                            Integer.parseInt(ajoutElement.wField.getText()),
                            Integer.parseInt(ajoutElement.hField.getText()),
                            Integer.parseInt(ajoutElement.dxField.getText()),
                            Integer.parseInt(ajoutElement.dyField.getText()),
                            ajoutElement.colorChooser.getColor()));
                }else{
                    scene.addElement(new ElementFixe(Integer.parseInt(ajoutElement.xField.getText()),
                            Integer.parseInt(ajoutElement.yField.getText()),
                            Integer.parseInt(ajoutElement.wField.getText()),
                            Integer.parseInt(ajoutElement.hField.getText()),
                            ajoutElement.colorChooser.getColor()));
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Parametre(s) invalide(s)", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setControlButon(ControlButton controlButton) {
        //ici, ajouter le controleur à tous les boutons cliquables
        // element.addActionListener(controlButton);
        bPause.addActionListener(controlButton);
        bAjouter.addActionListener(controlButton);
    }


}
