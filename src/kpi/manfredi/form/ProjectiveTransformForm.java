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
    private JButton undoButton;

    public JPanel getContent() {
        return content;
    }

    public ProjectiveTransformForm(GUIForm parent) {
        transformButton.addActionListener(e -> {
            try {
                DrawingPanel drawingPanel = (DrawingPanel) parent.getDrawingPanel();
                ProjectiveDTO projectiveDTO = new ProjectiveDTO(
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
                drawingPanel.setProjectiveDTO(projectiveDTO);
                parent.updateMeasurements();
                parent.repaintFigure();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.content, "Wrong input format.");
            }
        });
        undoButton.addActionListener(e -> {
            DrawingPanel drawingPanel = (DrawingPanel) parent.getDrawingPanel();
            drawingPanel.setProjectiveDTO(null);
            parent.updateMeasurements();
            parent.repaintFigure();
        });
    }
}
