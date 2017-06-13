package LO43;

import javax.swing.*;
import java.awt.*;


public class Vue extends JFrame{

    private Model model;

    public JButton bPause, bAjouter, bLireSauvegarde, bSauvegarder, bClear; // ....

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

        bPause = new JButton("Pause");
        bAjouter = new JButton("Ajouter");
        bClear = new JButton("Vider");
        bLireSauvegarde = new JButton("Lire...");
        bSauvegarder = new JButton("Sauvegarder...");

        barreLat.add(bPause);
        barreLat.add(bAjouter);
        barreLat.add(bLireSauvegarde);
        barreLat.add(bSauvegarder);
        barreLat.add(bClear);


        scene = new Scene(model);
        scene.setSize(1250,900);


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
        bClear.addActionListener(controlButton);
        bLireSauvegarde.addActionListener(controlButton);
        bSauvegarder.addActionListener(controlButton);
    }


}
