package kpi.manfredi.form;

import kpi.manfredi.DrawingPanel;
import kpi.manfredi.Figure;

import javax.swing.*;
import java.awt.*;

public class GUIForm {
    private volatile Figure figure;
//    private Dimension DRAWING_PANEL_DIMENSION = new Dimension(600, 600);

    private JPanel windowPanel;
    private JPanel previewPanel;
    private JPanel inputPanel;
    private JPanel drawingPanel;
    private JLabel previewImage;
    private JTextField W1TextField;
    private JTextField W2TextField;
    private JTextField H1TextField;
    private JTextField H2TextField;
    private JTextField H3TextField;
    private JTextField H4TextField;
    private JTextField H5TextField;
    private JTextField D1TextField;
    private JTextField R1TextField;
    private JTextField R2TextField;
    private JButton drawButton;
    private JMenu actionsMenu;
    private JMenu helpMenu;

    public JPanel getWindowPanel() {
        return windowPanel;
    }

    public Figure getFigure() {
        return figure;
    }

    public GUIForm(Figure figure) {
        this.figure = figure;
        updateMeasurements();
        drawButton.addActionListener(e -> {
            repaintFigure();
        });
    }

    public void repaintFigure() {
        updateMeasurements();
        drawingPanel.repaint();
        drawingPanel.revalidate();
    }

    private void updateMeasurements() {
        try {
            figure.setW1(Integer.parseInt(W1TextField.getText()));
            figure.setW2(Integer.parseInt(W2TextField.getText()));
            figure.setH1(Integer.parseInt(H1TextField.getText()));
            figure.setH2(Integer.parseInt(H2TextField.getText()));
            figure.setH3(Integer.parseInt(H3TextField.getText()));
            figure.setH4(Integer.parseInt(H4TextField.getText()));
            figure.setH5(Integer.parseInt(H5TextField.getText()));
            figure.setD1(Integer.parseInt(D1TextField.getText()));
            figure.setR1(Integer.parseInt(R1TextField.getText()));
            figure.setR2(Integer.parseInt(R2TextField.getText()));
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "You must to enter only the numbers!");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Nazarchuk D.K.");
        Figure figure = new Figure();
        GUIForm guiForm = new GUIForm(figure);
        frame.setContentPane(guiForm.getWindowPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1000, 700));
        frame.setVisible(true);
    }

    private void createUIComponents() {
        drawingPanel = new DrawingPanel(figure);
        drawingPanel.setMinimumSize(new Dimension(600, 600));
        ((DrawingPanel) drawingPanel).setGridVisible(true);

        //
        // menu begin
        //
        actionsMenu = new JMenu("Actions");
        JMenuItem moveMI = new JMenuItem("Move");
        actionsMenu.add(moveMI);
        moveMI.addActionListener(e -> {
            JFrame frame = new JFrame("Nazarchuk D.K.");
            MoveForm moveForm = new MoveForm(this);
            frame.setContentPane(moveForm.getContent());
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(250, 250));
            frame.setVisible(true);
        });
        JMenuItem transformMI = new JMenuItem("Transform");
        actionsMenu.add(transformMI);

        helpMenu = new JMenu("Help");
        JMenuItem helpMI = new JMenuItem("Help");
        helpMenu.add(helpMI);
        //
        // menu end
        //
    }
}
