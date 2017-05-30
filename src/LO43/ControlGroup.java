package LO43;


public class ControlGroup {

    public ControlGroup(Model m){

        Vue vue = new Vue(m);

        new ControlClavier(m, vue);
        new ControlButton(m, vue);

    }
}
