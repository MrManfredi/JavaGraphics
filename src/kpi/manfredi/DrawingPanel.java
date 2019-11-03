package kpi.manfredi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DrawingPanel extends JPanel implements IDrawingPanel{
    private Figure figure;

    private String nameX = "X";
    private String nameY = "Y";
    private int cellSize = 20;
    private int segmentSize = 40;
    private int padding = 40;
    private int labelPadding = 20;
    private boolean isGridVisible = false;

    public DrawingPanel(Figure figure) {
        this.figure = figure;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        if (isGridVisible) {
            drawGrid(graphics2D);
        }
//        drawAxis(graphics2D);
        drawFigure(graphics2D);
    }

    private void drawGrid(Graphics2D g) {
        Stroke baseStroke = g.getStroke();
        Paint basePaint = g.getPaint();
        g.setStroke(new BasicStroke(1));
        g.setPaint(Color.GRAY);

        int fullPadding = getFullPadding();

        for (int y = fullPadding; y <= fullPadding + getWorkspaceHeight(); y+= cellSize) {
            g.drawLine(fullPadding, y, fullPadding + getWorkspaceWidth(), y);
        }

        for (int x = fullPadding; x <= fullPadding + getWorkspaceWidth(); x+= cellSize) {
            g.drawLine(x, fullPadding, x, fullPadding + getWorkspaceHeight());
        }

        g.setStroke(baseStroke);
        g.setPaint(basePaint);
    }

    private int getWorkspaceHeight() {
        int tempHeight = getHeight() - 2 * padding - labelPadding;
        return tempHeight - tempHeight % cellSize;
    }

    private int getWorkspaceWidth() {
        int tempWidth = getWidth() - 2 * padding - labelPadding;
        return tempWidth - tempWidth % cellSize;
    }

    private void drawAxis(Graphics2D g) {
        Stroke baseStroke = g.getStroke();
        Paint basePaint = g.getPaint();
        g.setStroke(new BasicStroke(2));
        g.setFont(new Font("Serif", Font.PLAIN, cellSize / 2));
        g.setPaint(Color.BLACK);

        int fullPadding = getFullPadding();
        // axis
        g.drawLine(fullPadding, fullPadding - 1, fullPadding + getWorkspaceWidth(), fullPadding - 1);      // X
        g.drawLine(fullPadding - 1, fullPadding, fullPadding - 1, fullPadding + getWorkspaceHeight());     // Y

        // numbering
        g.drawString("O", cellSize * 0.6F, cellSize * 0.8F);
//        g.drawString("" + (cellSize * multiplier), cellSize * 1.6F, cellSize * 0.8F);
        g.drawString("1", cellSize * 1.6F, cellSize * 0.8F);
        g.drawString("1", cellSize * 0.6F, cellSize * 1.8F);

//        // x arrow
//        g.drawLine(getWidth(), cellSize, getWidth() - cellSize / 2, (int) (cellSize * 0.66));
//        g.drawLine(getWidth(), cellSize, getWidth() - cellSize / 2, (int) (cellSize * 1.33));
//
//        // y arrow
//        g.drawLine(cellSize, getHeight(), (int) (cellSize * 0.66), getHeight() - cellSize / 2);
//        g.drawLine(cellSize, getHeight(), (int) (cellSize * 1.33), getHeight() - cellSize / 2);


        g.setStroke(baseStroke);
        g.setPaint(basePaint);
    }

    private void drawContour(Graphics2D g, IContour contour) {
        List<Point2D> points = contour.getPoints();
        if (points == null || points.isEmpty()) {
            return;
        }

        Queue<Point2D> pointsQueue = new LinkedBlockingQueue<>();
        for (Point2D point2D : points) {
            Point2D temp = new Point2D.Double(point2D.getX() + getFullPadding(), point2D.getY() + getFullPadding());
            pointsQueue.add(temp);
        }

        Point2D temp = pointsQueue.remove();
        Point2D first = temp;
        while (!pointsQueue.isEmpty()) {
            g.draw(new Line2D.Double(temp, pointsQueue.peek()));
            temp = pointsQueue.remove();
        }
        g.draw(new Line2D.Double(temp, first));
    }

    private int getFullPadding() {
        return padding + labelPadding;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    private void drawFigure(Graphics2D g) {
        Stroke baseStroke = g.getStroke();
        Paint basePaint = g.getPaint();
        g.setStroke(new BasicStroke(3));
        g.setPaint(Color.RED);

        List<IContour> contours = figure.getContours();
        for (IContour contour : contours) {
            drawContour(g, contour);
        }

        g.setStroke(baseStroke);
        g.setPaint(basePaint);
    }


    @Override
    public void setAxis(String nameX, String nameY, int segmentSize) {
        this.nameX = nameX;
        this.nameY = nameY;
        this.segmentSize = segmentSize;
    }

    @Override
    public void setGridVisible(boolean isVisible) {
        isGridVisible = isVisible;
    }

    @Override
    public void setGridSize(int cellSize) {
        this.cellSize = cellSize;
    }
}
