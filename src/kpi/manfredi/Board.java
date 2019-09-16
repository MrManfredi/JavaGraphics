package kpi.manfredi;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.min;


public class Board extends JPanel {
    private Figure figure;
    private double multiplier;

    public Board(Figure figure) {
        this.figure = figure;
        multiplier = min(getWidth() * 0.8 / figure.getWidth(), getHeight() * 0.8 / (figure.getHeight() + figure.getRadiusOfRoundingBigger()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        int cellSize = 40;
        drawGrid(graphics2D, cellSize);
        drawFigure(graphics2D, cellSize);
    }

    private void drawGrid(Graphics2D g, int cellSize) {

        // axis
        g.drawLine(0, cellSize-1, getWidth(), cellSize-1);
        g.drawLine(cellSize-1, 0, cellSize-1, getHeight());

        g.drawString("O", cellSize * 0.6F, cellSize * 0.8F);
        g.drawString("1", cellSize * 1.6F, cellSize * 0.8F);
        g.drawString("1", cellSize * 0.6F, cellSize * 1.8F);

        // x arrow
        g.drawLine(getWidth(), cellSize, getWidth() - cellSize / 2, (int) (cellSize * 0.66));
        g.drawLine(getWidth(), cellSize - 1, getWidth() - cellSize / 2, (int) (cellSize * 0.66) - 1);
        g.drawLine(getWidth(), cellSize, getWidth() - cellSize / 2, (int) (cellSize * 1.33));
        g.drawLine(getWidth(), cellSize - 1, getWidth() - cellSize / 2, (int) (cellSize * 1.33) +1);

        // y arrow
        g.drawLine(cellSize, getHeight(), (int) (cellSize * 0.66), getHeight() - cellSize / 2);
        g.drawLine(cellSize - 1, getHeight(), (int) (cellSize * 0.66) - 1, getHeight() - cellSize / 2);
        g.drawLine(cellSize, getHeight(), (int) (cellSize * 1.33), getHeight() - cellSize / 2);
        g.drawLine(cellSize + 1, getHeight(), (int) (cellSize * 1.33) + 1, getHeight() - cellSize / 2);

        // cells
        g.setPaint(Color.GRAY);

        for (int y = 0; y < getHeight(); y+= cellSize) {
            g.drawLine(0, y, getWidth(), y);
        }

        for (int x = 0; x < getWidth(); x+= cellSize) {
            g.drawLine(x, 0, x, getHeight());
        }


    }

    private void drawFigure(Graphics2D g, int cellSize) {
//        int width = getWidth();
//        int height = getHeight();
//        g.drawLine(0, 0, width, height);
//        g.drawOval(width/2 - 50, height/2 - 50, 100, 100);
//        g.drawString("Height = " + figure.getHeight(), 100, 100);
//        g.drawString("Width = " + figure.getWidth(), 100, 120);

        int fulcrum = cellSize * 2;

        int rightSideX = (int) (fulcrum + figure.getWidth());
        int centerX = rightSideX / 2;



        g.setPaint(Color.yellow);

        // top
        g.drawLine(fulcrum, fulcrum, rightSideX, fulcrum);


        g.setPaint(Color.red);

        // left side
        g.drawLine(fulcrum, fulcrum, fulcrum, figure.getHeight());
        g.drawLine(fulcrum, figure.getHeight(), centerX - figure.getRadiusOfRoundingBigger(), figure.getHeight());


        g.setPaint(Color.blue);
        // right side
        g.drawLine(rightSideX, fulcrum, rightSideX, figure.getHeight());
        g.drawLine(rightSideX, figure.getHeight(), centerX + figure.getRadiusOfRoundingBigger(), figure.getHeight());




    }
}
