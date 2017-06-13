package LO43;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlButton implements ActionListener{

    private Model model;
    private Vue vue;

    public ControlButton(Model m, Vue v){
        model = m;
        vue = v;

        vue.setControlButon(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //ici, le code associé aux boutons

        if(e.getSource()==vue.bPause){
            if(vue.scene.isPaused()){
                // reprise
                vue.bPause.setText("Pause");
                vue.scene.setPause(false);
            }else {
                // pause
                vue.bPause.setText("Reprise");
                vue.scene.setPause(true);
            }
        }

        if(e.getSource()==vue.bAjouter){
            vue.ajouterElement();
        }

        if(e.getSource()== vue.bClear){
            if (JOptionPane.showConfirmDialog(vue, "Etes vous sur?", "Reinitialisation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                model.elements.clear();
            }
        }

        if(e.getSource()==vue.bSauvegarder){
            // pause
            vue.bPause.setText("Reprise");
            vue.scene.setPause(true);

            String nomSauvegarde = JOptionPane.showInputDialog(vue, "Nom de la sauvegarde", "Sauvegarder", JOptionPane.QUESTION_MESSAGE);
            if (!model.saveAlreadyExists(nomSauvegarde) ||
                    JOptionPane.showConfirmDialog(vue,"Une sauvegarde de ce nom existe deja. Voulez vous la remplacer?","Sauvegarder"
                            ,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
                model.sauvegarder(nomSauvegarde);
            }
        }

        if(e.getSource()==vue.bLireSauvegarde){
            // pause
            vue.bPause.setText("Reprise");
            vue.scene.setPause(true);

            DefaultListModel<String> defaultListModel = new DefaultListModel<>();
            for(String s : model.getSauvegardes()){
                defaultListModel.addElement(s);
            }
            JList<String> list = new JList<>(defaultListModel);
            if (JOptionPane.showConfirmDialog(vue, list, "choix sauvegarde", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
                model.lireSauvegarde(list.getSelectedValue());
            }
        }
    }
}
