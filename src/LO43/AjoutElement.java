package LO43;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AjoutElement extends JPanel{

    public static final int ELEMENT =0, ELEMENTFIXE =1, ELEMENTCONTROLABLE=2, ZONEACTIVABLE =3;

    public JFormattedTextField nom, xField, yField, hField, wField, dxField, dyField;
    public JRadioButton gauche, droite;
    public JComboBox<Element> elementLie;
    public int type;
    public JColorChooser colorChooser;

    public AjoutElement(int newType, ArrayList<Element> elements){
        super();
        type = newType;
        ArrayList<String> listeNoms = new ArrayList<>();
        for(Element e : elements){
            listeNoms.add(e.toString());
        }

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.setGroupingUsed(false);
        nom = new JFormattedTextField();
        nom.setInputVerifier(new NameInputVerifier(listeNoms));
        nom.setToolTipText("Ce nom ne doit pas deja exister");
        xField = new JFormattedTextField(decimalFormat);
        xField.setInputVerifier(new NumberInputVerifier(0,2000));
        xField.setToolTipText("Position sur l'axe X, positif");
        yField = new JFormattedTextField(decimalFormat);
        yField.setInputVerifier(new NumberInputVerifier(0,2000));
        yField.setToolTipText("Position sur l'axe Y, positif");
        hField = new JFormattedTextField(decimalFormat);
        hField.setInputVerifier(new NumberInputVerifier(1,500));
        hField.setToolTipText("Hauteur, entre 1 et 500");
        wField = new JFormattedTextField(decimalFormat);
        wField.setInputVerifier(new NumberInputVerifier(1,500));
        wField.setToolTipText("Largeur, entre 1 et 500");

        xField.setText("100");
        yField.setText("100");
        hField.setText("50");
        wField.setText("50");

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel optPan = new JPanel(new GridLayout(9,2,5,5));
        optPan.add(new JLabel("Nom Element"));
        optPan.add(nom);
        optPan.add(new JLabel("position X :"));
        optPan.add(xField);
        optPan.add(new JLabel("position Y :"));
        optPan.add(yField);

        optPan.add(new JLabel("hauteur : "));
        optPan.add(hField);
        optPan.add(new JLabel("largeur : "));
        optPan.add(wField);

        if (type == ELEMENT || type == ELEMENTCONTROLABLE){
            nom.setText("Element");
            dxField = new JFormattedTextField(decimalFormat);
            dxField.setInputVerifier(new NumberInputVerifier(-50,50));
            dxField.setToolTipText("Vitesse horizontale, 50 max \nnegatif -> gauche et positif -> droite");
            dyField = new JFormattedTextField(decimalFormat);
            dyField.setInputVerifier(new NumberInputVerifier(-50,50));
            dyField.setToolTipText("Vitesse verticale, 50 max \nnegatif -> haut et positif -> bas");

            dxField.setText("0");
            dyField.setText("0");

            optPan.add(new JLabel("deplacement X :"));
            optPan.add(dxField);
            optPan.add(new JLabel("deplacement Y :"));
            optPan.add(dyField);
        }

        if (type == ELEMENTCONTROLABLE){
            //gauche ou droite
            nom.setText("ElementControlable");
            gauche = new JRadioButton("Joueur Gauche (ZQSD)");
            droite = new JRadioButton("Joueur Droite (Fleches)");
            ButtonGroup group = new ButtonGroup();
            group.add(gauche);
            group.add(droite);

            optPan.add(gauche);
            optPan.add(droite);
        }

        if (type == ZONEACTIVABLE){
            //quel element
            nom.setText("ZoneActivable");
            elementLie = new JComboBox<>();
            elementLie.addItem(null);
            optPan.add(new JLabel("Element declencheur"));
            optPan.add(elementLie);
            for (Element e: elements){
                if (e.isMovable()) elementLie.addItem(e);
            }

        }

        if (type == ELEMENTFIXE){
            nom.setText("ElementFixe");
        }


        colorChooser = new JColorChooser(Color.gray);
        colorChooser.setPreviewPanel(new JPanel());
        add(optPan);
        add(colorChooser);
    }

}
