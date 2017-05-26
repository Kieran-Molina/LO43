package LO43;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kiéran on 26/05/2017.
 */
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
    }
}
