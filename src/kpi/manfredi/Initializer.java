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
        initFrame.setLayout(new GridLayout(11, 2));
//        initFrame.pack();
        initFrame.setSize(300, 350);
        initFrame.setBackground(Color.GRAY);

        // W1
        Label W1Label = new Label("W1:");
        initFrame.add(W1Label);
        TextField W1TextField = new TextField("20");
        initFrame.add(W1TextField);

        // W2
        Label W2Label = new Label("W2:");
        initFrame.add(W2Label);
        TextField W2TextField = new TextField("50");
        initFrame.add(W2TextField);

        // H1
        Label H1Label = new Label("H1:");
        initFrame.add(H1Label);
        TextField H1TextField = new TextField("10");
        initFrame.add(H1TextField);

        // H2
        Label H2Label = new Label("H2:");
        initFrame.add(H2Label);
        TextField H2TextField = new TextField("20");
        initFrame.add(H2TextField);

        // H3
        Label H3Label = new Label("H3:");
        initFrame.add(H3Label);
        TextField H3TextField = new TextField("25");
        initFrame.add(H3TextField);

        // H4
        Label H4Label = new Label("H4:");
        initFrame.add(H4Label);
        TextField H4TextField = new TextField("15");
        initFrame.add(H4TextField);

        // H5
        Label H5Label = new Label("H5:");
        initFrame.add(H5Label);
        TextField H5TextField = new TextField("40");
        initFrame.add(H5TextField);

        // D1
        Label D1Label = new Label("D1:");
        initFrame.add(D1Label);
        TextField D1TextField = new TextField("10");
        initFrame.add(D1TextField);

        // R1
        Label R1Label = new Label("R1:");
        initFrame.add(R1Label);
        TextField R1TextField = new TextField("10");
        initFrame.add(R1TextField);

        // R2
        Label R2Label = new Label("R2:");
        initFrame.add(R2Label);
        TextField R2TextField = new TextField("15");
        initFrame.add(R2TextField);

        // button
        Button submitButton = new Button("Submit");
        submitButton.addActionListener(e -> {

            try {
                synchronized (figure) {
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
