package LO43;

import javax.swing.*;

public class NumberInputVerifier extends InputVerifier {
    private int boundSup, boundInf;

    public NumberInputVerifier(int inf, int sup){
        super();
        boundInf = inf; boundSup = sup;
    }

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
            int i = Integer.parseInt(text);
            if (i < boundInf || i > boundSup) {
                JOptionPane.showMessageDialog(null,"entrez un nombre entre "+boundInf+" et "+boundSup, "valeur invalide", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"entrez un nombre entre "+boundInf+" et "+boundSup, "valeur invalide", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}