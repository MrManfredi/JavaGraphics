package kpi.manfredi.form;

import kpi.manfredi.Figure;

import javax.swing.*;

public class MoveForm {
    private JTextField moveAmountField;
    private JButton moveUpButton;
    private JButton moveDownButton;
    private JButton moveRightButton;
    private JButton moveLeftButton;
    private JPanel content;

    public MoveForm(GUIForm parent) {
        Figure figure = parent.getFigure();

        moveUpButton.addActionListener(e -> {
            figure.moveUp(Integer.parseInt(moveAmountField.getText()));
            parent.repaintFigure();
        });
        moveDownButton.addActionListener(e -> {
            figure.moveDown(Integer.parseInt(moveAmountField.getText()));
            parent.repaintFigure();
        });
        moveLeftButton.addActionListener(e -> {
            figure.moveLeft(Integer.parseInt(moveAmountField.getText()));
            parent.repaintFigure();
        });
        moveRightButton.addActionListener(e -> {
            figure.moveRight(Integer.parseInt(moveAmountField.getText()));
            parent.repaintFigure();
        });
    }

    public JPanel getContent() {
        return content;
    }
}
