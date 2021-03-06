package kpi.manfredi.form;

import kpi.manfredi.DrawingPanel;
import kpi.manfredi.Draft;
import kpi.manfredi.IFigure;
import kpi.manfredi.LemniscateOfBernoulli;

import javax.swing.*;
import java.awt.*;

public class GUIForm {
    private volatile IFigure figure;
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

    public JPanel getDrawingPanel() {
        return drawingPanel;
    }

    public IFigure getFigure() {
        return figure;
    }

    public void setFigure(IFigure figure) {
        this.figure = figure;
        ((DrawingPanel)drawingPanel).setFigure(figure);
    }

    public GUIForm(IFigure figure) {
        this.figure = figure;
        if (figure instanceof Draft) setMeasurements();
        drawButton.addActionListener(e -> {
            setMeasurements();
            repaintFigure();
        });
    }

    public void repaintFigure() {
        drawingPanel.repaint();
        drawingPanel.revalidate();
    }

    private void setMeasurements() {
        try {
            ((Draft)figure).setW1(Double.parseDouble(W1TextField.getText()));
            ((Draft)figure).setW2(Double.parseDouble(W2TextField.getText()));
            ((Draft)figure).setH1(Double.parseDouble(H1TextField.getText()));
            ((Draft)figure).setH2(Double.parseDouble(H2TextField.getText()));
            ((Draft)figure).setH3(Double.parseDouble(H3TextField.getText()));
            ((Draft)figure).setH4(Double.parseDouble(H4TextField.getText()));
            ((Draft)figure).setH5(Double.parseDouble(H5TextField.getText()));
            ((Draft)figure).setD1(Double.parseDouble(D1TextField.getText()));
            ((Draft)figure).setR1(Double.parseDouble(R1TextField.getText()));
            ((Draft)figure).setR2(Double.parseDouble(R2TextField.getText()));
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "You must to enter only the numbers!");
        }
    }

    public void updateMeasurements() {
        W1TextField.setText(String.valueOf(((Draft)figure).getW1()));
        W2TextField.setText(String.valueOf(((Draft)figure).getW2()));
        H1TextField.setText(String.valueOf(((Draft)figure).getH1()));
        H2TextField.setText(String.valueOf(((Draft)figure).getH2()));
        H3TextField.setText(String.valueOf(((Draft)figure).getH3()));
        H4TextField.setText(String.valueOf(((Draft)figure).getH4()));
        H5TextField.setText(String.valueOf(((Draft)figure).getH5()));
        D1TextField.setText(String.valueOf(((Draft)figure).getD1()));
        R1TextField.setText(String.valueOf(((Draft)figure).getR1()));
        R2TextField.setText(String.valueOf(((Draft)figure).getR2()));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Nazarchuk D.K.");

        IFigure figure = new Draft();   // new LemniscateOfBernoulli(150);
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
        createMenu();
    }

    private void createMenu() {
        actionsMenu = new JMenu("Actions");

        JMenuItem moveMI = new JMenuItem("Move");
        actionsMenu.add(moveMI);
        moveMI.addActionListener(e -> {
            JFrame frame = new JFrame("Move");
            MoveForm moveForm = new MoveForm(this);
            frame.setContentPane(moveForm.getContent());
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(250, 250));
            frame.setVisible(true);
        });

        JMenuItem rotateMI = new JMenuItem("Rotate");
        actionsMenu.add(rotateMI);
        rotateMI.addActionListener(e -> {
            JFrame frame = new JFrame("Rotate");
            RotateForm rotateForm = new RotateForm(this);
            frame.setContentPane(rotateForm.getContent());
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(250, 250));
            frame.setVisible(true);
        });

        JMenuItem scaleMI = new JMenuItem("Scale");
        actionsMenu.add(scaleMI);
        scaleMI.addActionListener(e -> {
            JFrame frame = new JFrame("Scale");
            ScaleForm scaleForm = new ScaleForm(this);
            frame.setContentPane(scaleForm.getContent());
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(250, 100));
            frame.setVisible(true);
        });

        JMenuItem auxiliaryLinesMI = new JMenuItem("Auxiliary lines");
        actionsMenu.add(auxiliaryLinesMI);
        auxiliaryLinesMI.addActionListener(e -> {
            JFrame frame = new JFrame("Auxiliary lines");
            AuxiliaryLinesForm auxiliaryLinesForm = new AuxiliaryLinesForm(this);
            frame.setContentPane(auxiliaryLinesForm.getContent());
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(400, 150));
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
        });

        JMenuItem afinneTransformMI = new JMenuItem("Afinne Transform");
        actionsMenu.add(afinneTransformMI);
        afinneTransformMI.addActionListener(e -> {
            JFrame frame = new JFrame("Afinne Transform");
            AfinneTransformForm afinneTransformForm = new AfinneTransformForm(this);
            frame.setContentPane(afinneTransformForm.getContent());
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(400, 300));
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
        });

        JMenuItem projectiveTransformMI = new JMenuItem("Projective Transform");
        actionsMenu.add(projectiveTransformMI);
        projectiveTransformMI.addActionListener(e -> {
            JFrame frame = new JFrame("Projective Transform");
            ProjectiveTransformForm projectiveTransformForm = new ProjectiveTransformForm(this);
            frame.setContentPane(projectiveTransformForm.getContent());
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(600, 300));
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
        });

        JMenuItem curveMI = new JMenuItem("Lemniscate of Bernoulli");
        actionsMenu.add(curveMI);
        curveMI.addActionListener(e -> {
            JFrame frame = new JFrame("Lemniscate of Bernoulli");
            CurveForm curveForm = new CurveForm(this);
            frame.setContentPane(curveForm.getContent());
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(400, 150));
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
        });

        helpMenu = new JMenu("Help");
        JMenuItem helpMI = new JMenuItem("Help");
        helpMenu.add(helpMI);
    }
}
