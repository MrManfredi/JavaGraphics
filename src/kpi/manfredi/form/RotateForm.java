package kpi.manfredi.form;

import javax.swing.*;

public class RotateForm {
    private JPanel content;
    private JTextField xTextField;
    private JTextField yTextField;
    private JTextField angleTextField;
    private JButton rotateButton;

    public JPanel getContent() {
        return content;
    }

    public RotateForm(GUIForm parent) {
        rotateButton.addActionListener(e -> {
            parent.getFigure().rotate(
                    Integer.parseInt(xTextField.getText()),
                    Integer.parseInt(yTextField.getText()),
                    Integer.parseInt(angleTextField.getText()));
            parent.repaintFigure();
        });
    }
}
