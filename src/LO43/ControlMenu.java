package LO43;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlMenu implements ActionListener{
    private Model model;
    private Vue vue;

    public ControlMenu(Model m, Vue v){
        model = m;
        vue = v;

        vue.setControlMenu(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //code en cas de clic sur un élément de la barre de menu

    }
}
