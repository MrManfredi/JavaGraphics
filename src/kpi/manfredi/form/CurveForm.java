package kpi.manfredi.form;

import kpi.manfredi.LemniscateOfBernoulli;

import javax.swing.*;

public class CurveForm {
    private JPanel content;
    private JTextField distanceTF;
    private JButton drawButton;

    public JPanel getContent() {
        return content;
    }

    public CurveForm(GUIForm guiForm) {
        drawButton.addActionListener(e -> {
            try {
                guiForm.setFigure(new LemniscateOfBernoulli(Double.parseDouble(distanceTF.getText()) / 2.0));
                guiForm.repaintFigure();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.content, "Wrong input format.");
            }
        });
    }
}
