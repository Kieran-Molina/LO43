package LO43;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class AjoutElement extends JPanel{

    public JFormattedTextField xField, yField, hField, wField, dxField, dyField;
    public JCheckBox movable;
    public JColorChooser colorChooser;

    public AjoutElement(){
        super();

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.setGroupingUsed(false);
        xField = new JFormattedTextField(decimalFormat);
        yField = new JFormattedTextField(decimalFormat);
        hField = new JFormattedTextField(decimalFormat);
        wField = new JFormattedTextField(decimalFormat);
        dxField = new JFormattedTextField(decimalFormat);
        dyField = new JFormattedTextField(decimalFormat);

        movable = new JCheckBox("deplacable", true);

        colorChooser = new JColorChooser(Color.black);

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel optPan = new JPanel(new GridLayout(7,2,5,5));
        optPan.add(new JLabel("position X :"));
        optPan.add(xField);
        optPan.add(new JLabel("position Y :"));
        optPan.add(yField);

        optPan.add(new JLabel("hauteur : "));
        optPan.add(hField);
        optPan.add(new JLabel("largeur : "));
        optPan.add(wField);

        optPan.add(new JLabel("deplacement X :"));
        optPan.add(dxField);
        optPan.add(new JLabel("deplacement Y :"));
        optPan.add(dyField);

        optPan.add(movable);

        add(optPan);
        add(colorChooser);
    }

}
