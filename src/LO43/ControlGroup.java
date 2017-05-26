package LO43;

/**
 * Created by Kiéran on 26/05/2017.
 */
public class ControlGroup {

    private Model model;
    private Vue vue;

    private ControlMenu cm;
    private ControlButton cb;

    public ControlGroup(Model m){
        model = m;

        vue = new Vue(model);

        cm = new ControlMenu(model, vue);
        cb = new ControlButton(model,vue);

    }
}
