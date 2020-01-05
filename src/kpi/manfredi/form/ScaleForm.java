package kpi.manfredi.form;

import javax.swing.*;

public class ScaleForm {
    private JPanel content;
    private JTextField scalingFactor;
    private JButton scaleButton;

    public JPanel getContent() {
        return content;
    }

    public ScaleForm(GUIForm parent) {
        scaleButton.addActionListener(e -> {
            try {
                parent.getFigure().toScale(Double.parseDouble(scalingFactor.getText()));
                parent.updateMeasurements();
                parent.repaintFigure();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.content, "Wrong input format.");
            }
        });
    }
}
