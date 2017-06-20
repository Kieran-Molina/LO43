package LO43;

import javax.swing.*;
import java.util.ArrayList;

public class NameInputVerifier extends InputVerifier {
    private ArrayList<String> listeNoms;

    public NameInputVerifier(ArrayList<String> noms){
        super();
        listeNoms = noms;
    }

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();

        for (String nom : listeNoms){
            if (nom.equals(text)) {
                JOptionPane.showMessageDialog(null,"ce nom existe deja", "nom existant", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }
}