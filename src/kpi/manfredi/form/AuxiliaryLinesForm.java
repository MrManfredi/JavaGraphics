package kpi.manfredi.form;

import kpi.manfredi.DrawingPanel;
import kpi.manfredi.Figure;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class AuxiliaryLinesForm {
    private JPanel content;
    private JTextField X;
    private JTextField Y;
    private JCheckBox tangentLineCheckBox;
    private JCheckBox perpendicularCheckBox;
    private Point2D point2D;
    private GUIForm parent;
    private DrawingPanel drawingPanel;
    private double fullPadding;

    public JPanel getContent() {
        return content;
    }

    public AuxiliaryLinesForm(GUIForm parent) {
        this.parent = parent;
        drawingPanel = (DrawingPanel) parent.getDrawingPanel();
        point2D = drawingPanel.getBasisAuxiliaryLinesPoint();
        if (point2D != null) {
            X.setText(String.valueOf(point2D.getX()));
            Y.setText(String.valueOf(point2D.getY()));
        }
        tangentLineCheckBox.setSelected(drawingPanel.isTangentLineCheckBox());
        perpendicularCheckBox.setSelected(drawingPanel.isPerpendicularCheckBox());
        fullPadding = drawingPanel.getFullPadding();

        drawingPanel.addMouseListener(new MouseClickListener());
        tangentLineCheckBox.addActionListener(e -> {
            drawingPanel.setTangentLineCheckBox(tangentLineCheckBox.isSelected());
            parent.repaintFigure();
        });
        perpendicularCheckBox.addActionListener(e -> {
            drawingPanel.setPerpendicularCheckBox(perpendicularCheckBox.isSelected());
            parent.repaintFigure();
        });
    }

    private class MouseClickListener implements MouseInputListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            Point point = e.getPoint();
            point2D = new Point2D.Double(point.getX() - fullPadding, point.getY() - fullPadding);
            Figure figure = parent.getFigure();
            Point2D figurePoint = figure.getPoint(point2D);
            if (figurePoint != null) {
                X.setText(String.valueOf(figurePoint.getX()));
                Y.setText(String.valueOf(figurePoint.getY()));
            } else {
                X.setText("");
                Y.setText("");
            }
            drawingPanel.setBasisAuxiliaryLinesPoint(figurePoint);
            parent.repaintFigure();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
