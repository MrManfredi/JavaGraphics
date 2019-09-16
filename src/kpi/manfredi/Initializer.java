package kpi.manfredi;

import javax.swing.*;
import java.awt.*;

public class Initializer extends Thread{
    private volatile Figure figure;

    public Initializer(Figure figure) {
        this.figure = figure;
    }

    @Override
    public void run() {
        JFrame initFrame = new JFrame("Initialization");
        initFrame.setLayout(new GridLayout(7, 2));
//        initFrame.pack();
        initFrame.setSize(400, 250);
        initFrame.setBackground(Color.GRAY);

        // width
        Label widthLabel = new Label("Width:");
        initFrame.add(widthLabel);

        TextField widthTextField = new TextField("50");
        initFrame.add(widthTextField);

        // the distance to the hole
        Label distanceToHoleLabel = new Label("Distance to the hole:");
        initFrame.add(distanceToHoleLabel);

        TextField distanceToHoleTextField = new TextField("40");
        initFrame.add(distanceToHoleTextField);

        // height
        Label heightLabel = new Label("Height:");
        initFrame.add(heightLabel);

        TextField heightTextField = new TextField("15");
        initFrame.add(heightTextField);

        // hole diameter
        Label holeDiameterLabel = new Label("Diameter of the hole:");
        initFrame.add(holeDiameterLabel);

        TextField holeDiameterTextField = new TextField("10");
        initFrame.add(holeDiameterTextField);

        // radius of rounding (bigger)
        Label radiusOfRoundingBiggerLabel = new Label("Radius of rounding (bigger):");
        initFrame.add(radiusOfRoundingBiggerLabel);

        TextField radiusOfRoundingBiggerTextField = new TextField("15");
        initFrame.add(radiusOfRoundingBiggerTextField);

        // radius of rounding (smaller)
        Label radiusOfRoundingSmallerLabel = new Label("Radius of rounding (smaller):");
        initFrame.add(radiusOfRoundingSmallerLabel);

        TextField radiusOfRoundingSmallerTextField = new TextField("10");
        initFrame.add(radiusOfRoundingSmallerTextField);

        // button
        Button submitButton = new Button("Submit");
        submitButton.addActionListener(e -> {

            try {
                synchronized (figure) {
                    figure.setWidth(Integer.parseInt(widthTextField.getText()));
                    figure.setDistanceToHole(Integer.parseInt(distanceToHoleTextField.getText()));
                    figure.setHeight(Integer.parseInt(heightTextField.getText()));
                    figure.setHoleDiameter(Integer.parseInt(holeDiameterTextField.getText()));
                    figure.setRadiusOfRoundingBigger(Integer.parseInt(radiusOfRoundingBiggerTextField.getText()));
                    figure.setRadiusOfRoundingSmaller(Integer.parseInt(radiusOfRoundingSmallerTextField.getText()));
                    figure.setFine(true);
                    figure.notify();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(initFrame.getRootPane(), "Wrong data!");
                figure.setFine(false);
            }
        });
        initFrame.add(new Label("Fill the fields and click:"));
        initFrame.add(submitButton);

        // settings
        initFrame.setVisible(true);
        initFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
