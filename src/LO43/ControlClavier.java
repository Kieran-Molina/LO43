package LO43;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ControlClavier{
    private Model model;
    private Vue vue;

    public ControlClavier(Model m, Vue v){
        model = m;
        vue = v;

        InputMap inputMap = vue.scene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = vue.scene.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "D_U");
        am.put("D_U", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saut JD
                for (Element element: vue.scene.elements){
                    if(element.isControlDroite() && element.isLand()){
                        element.setDy(-15);
                        element.setLand(false);
                    }
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "D_R");
        am.put("D_R", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Element element: vue.scene.elements){
                    if(element.isControlDroite()){
                        element.setDx(9);
                    }
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "D_L");
        am.put("D_L", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Element element: vue.scene.elements){
                    if(element.isControlDroite()){
                        element.setDx(-9);
                    }
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), "G_U");
        am.put("G_U", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saut JD
                for (Element element: vue.scene.elements){
                    if(element.isControlGauche() && element.isLand()){
                        element.setDy(-15);
                        element.setLand(false);
                    }
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "G_R");
        am.put("G_R", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Element element: vue.scene.elements){
                    if(element.isControlGauche()){
                        element.setDx(9);
                    }
                }
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "G_L");
        am.put("G_L", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Element element: vue.scene.elements){
                    if(element.isControlGauche()){
                        element.setDx(-9);
                    }
                }
            }
        });
    }
}
