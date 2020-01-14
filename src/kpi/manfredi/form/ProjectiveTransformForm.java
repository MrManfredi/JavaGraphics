package kpi.manfredi.form;

import kpi.manfredi.DrawingPanel;

import javax.swing.*;

public class ProjectiveTransformForm {
    private JPanel content;

    private JTextField xx;
    private JTextField yx;
    private JTextField ox;

    private JTextField xy;
    private JTextField yy;
    private JTextField oy;

    private JTextField wx;
    private JTextField wy;
    private JTextField wo;

    private JButton transformButton;

    public JPanel getContent() {
        return content;
    }

    public ProjectiveTransformForm(GUIForm parent) {
        transformButton.addActionListener(e -> {
            try {
                DrawingPanel drawingPanel = (DrawingPanel) parent.getDrawingPanel();

                parent.getFigure().projectiveTransform(
                        Double.parseDouble(this.xx.getText()),
                        Double.parseDouble(this.xy.getText()),
                        Double.parseDouble(this.yx.getText()),
                        Double.parseDouble(this.yy.getText()),
                        Double.parseDouble(this.ox.getText()),
                        Double.parseDouble(this.oy.getText()),
                        Double.parseDouble(this.wx.getText()),
                        Double.parseDouble(this.wy.getText()),
                        Double.parseDouble(this.wo.getText())
                );
                parent.updateMeasurements();
                parent.repaintFigure();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.content, "Wrong input format.");
            }
        });
    }
}
