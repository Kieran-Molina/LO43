package LO43;

/**
 * Created by Kiéran on 26/05/2017.
 */
public class Launcher {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Model model = new Model();
                ControlGroup control = new ControlGroup(model);
            }
        });
    }
}