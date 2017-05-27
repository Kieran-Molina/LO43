package LO43;

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
                vue.bPause.setText("pause");
                vue.scene.setPause(false);
            }else {
                // pause
                vue.bPause.setText("reprise");
                vue.scene.setPause(true);
            }
        }

        if(e.getSource()==vue.bAjouter){
            vue.ajouterElement();
        }
    }
}
