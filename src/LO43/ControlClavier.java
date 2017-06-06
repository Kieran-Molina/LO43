package LO43;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class ControlClavier{
    private Model model;
    private Vue vue;

    public ControlClavier(Model m, Vue v){
        model = m;
        vue = v;



        InputMap inputMap = vue.scene.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = vue.scene.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,false), "D_U");
        am.put("D_U", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.du = true;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,true), "D_Ur");
        am.put("D_Ur", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.du = false;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "D_R");
        am.put("D_R", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.dr= true;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "D_Rr");
        am.put("D_Rr", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.dr= false;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0,false), "D_L");
        am.put("D_L", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.dl = true;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0,true), "D_Lr");
        am.put("D_Lr", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.dl = false;
            }
        });



        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, false), "G_U");
        am.put("G_U", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gu = true;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, true), "G_Ur");
        am.put("G_Ur", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gu = false;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "G_R");
        am.put("G_R", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gr = true;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "G_Rr");
        am.put("G_Rr", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gr = false;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0,false), "G_L");
        am.put("G_L", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gl = true;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0,true), "G_Lr");
        am.put("G_Lr", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gl = false;
            }
        });
    }
}
