package LO43;

import javax.swing.*;
import java.awt.*;


public class Vue extends JFrame{

    private Model model;

    public JButton bPause, bAjouter, bLireSauvegarde, bSauvegarder, bClear, bSupprElement;

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
        bSupprElement = new JButton("Supprimer");
        bClear = new JButton("Vider");
        bLireSauvegarde = new JButton("Lire...");
        bSauvegarder = new JButton("Sauvegarder...");

        barreLat.add(bPause);
        barreLat.add(bAjouter);
        barreLat.add(bSupprElement);
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
        JPanel typePanel = new JPanel(new GridLayout(2,2,5,5));
        ButtonGroup group = new ButtonGroup();
        JRadioButton rElement = new JRadioButton("Element Deplacable", true);
        JRadioButton rElementF = new JRadioButton("Element Fixe");
        JRadioButton rElementC = new JRadioButton("Element Controlable");
        JRadioButton rZone = new JRadioButton("Zone Activable");

        group.add(rElement); group.add(rElementF); group.add(rElementC); group.add(rZone);
        typePanel.add(rElement); typePanel.add(rElementF); typePanel.add(rElementC); typePanel.add(rZone);

        int res1, res2=-1;
        do {
            res1 = JOptionPane.showConfirmDialog(this, typePanel, "Choisir un type", JOptionPane.OK_CANCEL_OPTION);
            if (res1 == JOptionPane.OK_OPTION){
                AjoutElement ae;
                if (rElement.isSelected()) ae = new AjoutElement(AjoutElement.ELEMENT, model.elements);
                else if (rElementC.isSelected()) ae = new AjoutElement(AjoutElement.ELEMENTCONTROLABLE, model.elements);
                else if (rElementF.isSelected()) ae = new AjoutElement(AjoutElement.ELEMENTFIXE, model.elements);
                else ae = new AjoutElement(AjoutElement.ZONEACTIVABLE, model.elements);

                res2 = JOptionPane.showConfirmDialog(this, ae,
                        "Parametre du nouvel element", JOptionPane.OK_CANCEL_OPTION);
                if(res2 == JOptionPane.OK_OPTION) {

                    if (rElement.isSelected()) {
                        scene.addElement(new Element(ae.nom.getText(),
                                Integer.parseInt(ae.xField.getText()),
                                Integer.parseInt(ae.yField.getText()),
                                Integer.parseInt(ae.wField.getText()),
                                Integer.parseInt(ae.hField.getText()),
                                Integer.parseInt(ae.dxField.getText()),
                                Integer.parseInt(ae.dyField.getText()),
                                ae.colorChooser.getColor()));
                    }else
                    if (rElementC.isSelected()){
                        scene.addElement(new ElementControlable(ae.nom.getText(),
                                Integer.parseInt(ae.xField.getText()),
                                Integer.parseInt(ae.yField.getText()),
                                Integer.parseInt(ae.wField.getText()),
                                Integer.parseInt(ae.hField.getText()),
                                Integer.parseInt(ae.dxField.getText()),
                                Integer.parseInt(ae.dyField.getText()),
                                ae.colorChooser.getColor(),
                                ae.gauche.isSelected()));
                    }else
                    if (rElementF.isSelected()){
                        scene.addElement(new ElementFixe(ae.nom.getText(),
                                Integer.parseInt(ae.xField.getText()),
                                Integer.parseInt(ae.yField.getText()),
                                Integer.parseInt(ae.wField.getText()),
                                Integer.parseInt(ae.hField.getText()),
                                ae.colorChooser.getColor()
                                ));
                    }else
                    if (rZone.isSelected()){
                        scene.addElement(new ZoneActivable(ae.nom.getText(),
                                Integer.parseInt(ae.xField.getText()),
                                Integer.parseInt(ae.yField.getText()),
                                Integer.parseInt(ae.wField.getText()),
                                Integer.parseInt(ae.hField.getText()),
                                ae.colorChooser.getColor(),
                                ae.gauche.isSelected()? ZoneActivable.STAY_ON : ZoneActivable.ACTIVATE_ONCE,
                                (Element) ae.elementLie.getSelectedItem()
                        ));
                    }
                }
            }

        }while (res1 == JOptionPane.OK_OPTION && res2 == JOptionPane.CANCEL_OPTION);
    }

    public void supprimerElement(){
        JPanel optPan = new JPanel(new GridLayout(1,2,5,5));
        JComboBox<Element> elementCB = new JComboBox<>();
        optPan.add(new JLabel("Element a supprimer"));
        optPan.add(elementCB);
        for (Element e: model.elements){
            elementCB.addItem(e);
        }

        if(JOptionPane.showConfirmDialog(this,optPan,"Selectionnez l'element a supprimer",JOptionPane.OK_CANCEL_OPTION)
                == JOptionPane.OK_OPTION){
            model.elements.remove((Element)elementCB.getSelectedItem());
        }
    }

    public void setControlButon(ControlButton controlButton) {
        //ici, ajouter le controleur à tous les boutons cliquables
        // element.addActionListener(controlButton);
        bPause.addActionListener(controlButton);
        bAjouter.addActionListener(controlButton);
        bSupprElement.addActionListener(controlButton);
        bClear.addActionListener(controlButton);
        bLireSauvegarde.addActionListener(controlButton);
        bSauvegarder.addActionListener(controlButton);
    }


}
