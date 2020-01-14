package kpi.manfredi.form;

import kpi.manfredi.DrawingPanel;

import javax.swing.*;

public class AfinneTransformForm {
    private JPanel content;
    private JTextField xx;
    private JTextField yx;
    private JTextField xy;
    private JTextField yy;
    private JTextField ox;
    private JTextField oy;
    private JButton transformButton;

    public JPanel getContent() {
        return content;
    }

    public AfinneTransformForm(GUIForm parent) {
        transformButton.addActionListener(e -> {
            try {
                DrawingPanel drawingPanel = (DrawingPanel) parent.getDrawingPanel();

                parent.getFigure().afinneTransform(
                        Double.parseDouble(this.xx.getText()),
                        Double.parseDouble(this.xy.getText()),
                        Double.parseDouble(this.yx.getText()),
                        Double.parseDouble(this.yy.getText()),
                        Double.parseDouble(this.ox.getText()),
                        Double.parseDouble(this.oy.getText())
                );
                parent.updateMeasurements();
                parent.repaintFigure();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.content, "Wrong input format.");
            }
        });
    }
}
