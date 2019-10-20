package kpi.manfredi;

import javax.swing.*;

public class Workplace extends Thread{
    private volatile Figure figure;

    public Workplace(Figure figure) {
        this.figure = figure;
    }
    @Override
    public void run() {


        while (true) {
            synchronized (figure) {
                try {
                    figure.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            JFrame jFrame = new JFrame("Zero");
            jFrame.setSize(1000, 700);
            jFrame.setVisible(true);
            Board board = new Board(figure, jFrame.getSize());
            jFrame.add(board);
            jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jFrame.setResizable(false);
        }
    }
}
