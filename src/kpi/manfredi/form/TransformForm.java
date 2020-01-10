package kpi.manfredi.form;

import kpi.manfredi.DrawingPanel;

import javax.swing.*;

public class TransformForm {
    private JPanel content;
    private JTextField transformFactor;
    private JButton transformButton;


    public JPanel getContent() {
        return content;
    }

    public TransformForm(GUIForm parent) {
        transformButton.addActionListener(e -> {
            try {
                DrawingPanel drawingPanel = (DrawingPanel) parent.getDrawingPanel();
                double transformFactor = Double.parseDouble(this.transformFactor.getText());
                drawingPanel.changeTransformFactor(transformFactor);
                parent.getFigure().transform(transformFactor);
                parent.updateMeasurements();
                parent.repaintFigure();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.content, "Wrong input format.");
            }
        });
    }
}
