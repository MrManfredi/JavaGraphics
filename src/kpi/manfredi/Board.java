package kpi.manfredi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Math.min;


public class Board extends JPanel {
    private Figure figure;
    private double multiplier;

    public Board(Figure figure, Dimension dimension) {
        double v1 = dimension.getWidth() * 0.8 / figure.getW2();
        double v2 = dimension.getHeight() * 0.8 / (figure.getH4() + figure.getH5() + figure.getR2());
        multiplier = min(v1,v2);
        this.figure = figure;
//        this.figure = new Figure(figure, multiplier);
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
        Stroke baseStroke = g.getStroke();
        g.setStroke(new BasicStroke(3));
        Paint basePaint = g.getPaint();

        g.setFont(new Font("Serif", Font.PLAIN, cellSize / 2));

        g.setPaint(Color.BLACK);
        // axis
        g.drawLine(0, cellSize-1, getWidth(), cellSize-1);
        g.drawLine(cellSize-1, 0, cellSize-1, getHeight());

        // numbering
        g.drawString("O", cellSize * 0.6F, cellSize * 0.8F);
//        g.drawString("" + (cellSize * multiplier), cellSize * 1.6F, cellSize * 0.8F);
        g.drawString("1", cellSize * 1.6F, cellSize * 0.8F);
        g.drawString("1", cellSize * 0.6F, cellSize * 1.8F);

        // x arrow
        g.drawLine(getWidth(), cellSize, getWidth() - cellSize / 2, (int) (cellSize * 0.66));
        g.drawLine(getWidth(), cellSize, getWidth() - cellSize / 2, (int) (cellSize * 1.33));

        // y arrow
        g.drawLine(cellSize, getHeight(), (int) (cellSize * 0.66), getHeight() - cellSize / 2);
        g.drawLine(cellSize, getHeight(), (int) (cellSize * 1.33), getHeight() - cellSize / 2);


        // cells
        g.setStroke(new BasicStroke(1));
        g.setPaint(Color.GRAY);

        for (int y = 0; y < getHeight(); y+= cellSize) {
            g.drawLine(0, y, getWidth(), y);
        }

        for (int x = 0; x < getWidth(); x+= cellSize) {
            g.drawLine(x, 0, x, getHeight());
        }

        g.setStroke(baseStroke);
        g.setPaint(basePaint);
    }

    private void drawFigure(Graphics2D g, int cellSize) {
        int fulcrum = cellSize * 4;

        g.setStroke(new BasicStroke(3));
        g.setPaint(Color.RED);

        Point2D.Double A = new Point2D.Double(fulcrum, fulcrum);
        Point2D.Double B = new Point2D.Double(fulcrum + figure.getW2(), fulcrum);
        Point2D.Double O0 = new Point2D.Double(fulcrum + figure.getW2() / 2.0, fulcrum);
        Point2D.Double C = new Point2D.Double(fulcrum, fulcrum + figure.getH4());
        Point2D.Double D = new Point2D.Double(B.getX(), C.getY());
        Point2D.Double E = new Point2D.Double(O0.getX() - figure.getR2(), C.getY());
        Point2D.Double F = new Point2D.Double(O0.getX() + figure.getR2(), C.getY());
        Point2D.Double G = new Point2D.Double(E.getX(), fulcrum + figure.getH5());
        Point2D.Double H = new Point2D.Double(F.getX(), G.getY());
        Point2D.Double O1 = new Point2D.Double(O0.getX(), G.getY() - figure.getH3());
        Point2D.Double I1 = new Point2D.Double(O1.getX() - figure.getR1(), O1.getY());
        Point2D.Double I2 = new Point2D.Double(O1.getX() - figure.getW1() / 2, O1.getY());
        Point2D.Double J1 = new Point2D.Double(O1.getX() + figure.getR1(), O1.getY());
        Point2D.Double J2 = new Point2D.Double(O1.getX() + figure.getW1() / 2, O1.getY());
        Point2D.Double O2 = new Point2D.Double(O0.getX(), G.getY());
        Point2D.Double L = new Point2D.Double(I2.getX(), E.getY() + figure.getH2());
        Point2D.Double M = new Point2D.Double(J2.getX(), L.getY());
        Point2D.Double K = new Point2D.Double(O0.getX(), L.getY() - figure.getH1());
        Point2D.Double O3 = new Point2D.Double(O0.getX(), O2.getY() + figure.getR2());

        g.draw(new Line2D.Double(A, B));
        g.draw(new Line2D.Double(A, C));
        g.draw(new Line2D.Double(B, D));
        g.draw(new Line2D.Double(C, E));
        g.draw(new Line2D.Double(F, D));
        g.draw(new Line2D.Double(E, G));
        g.draw(new Line2D.Double(F, H));
        g.draw(new Line2D.Double(I1, I2));
        g.draw(new Line2D.Double(J1, J2));
        g.draw(new Line2D.Double(I2, L));
        g.draw(new Line2D.Double(J2, M));
        g.draw(new Line2D.Double(K, M));
        g.draw(new Line2D.Double(K, L));

        List<Point2D.Double> arcO1R1Points = getCirclePoints(O1, figure.getR1(), Math.PI, Math.PI * 2); // name structure: figure_center_radius_type
        drawTheBrokenLine(g, arcO1R1Points);

        List<Point2D.Double> circleO2D1Points = getCirclePoints(O2, figure.getD1() / 2, 0, Math.PI * 2);
        drawTheBrokenLine(g, circleO2D1Points);

        List<Point2D.Double> arcO2R2Points2 = getCirclePoints(O2, figure.getR2(), 0, Math.PI);
        drawTheBrokenLine(g, arcO2R2Points2);

        g.setStroke(new BasicStroke(1));
        g.setPaint(Color.YELLOW);
    }

    private List<Point2D.Double> getCirclePoints(Point2D.Double center, double radius, double angleFrom, double angleTo) {
        List<Point2D.Double> circlePoints = new ArrayList<>();
        double currentAngle = angleFrom;
        while (currentAngle <= angleTo + 0.001) {
            Point2D.Double temp = new Point2D.Double();
            double xLocation = radius * Math.cos(currentAngle) + center.getX();
            double yLocation = radius * Math.sin(currentAngle) + center.getY();
            temp.setLocation(xLocation, yLocation);
            circlePoints.add(temp);
            currentAngle+= Math.PI / 20;
        }
        return circlePoints;
    }

    private void drawTheBrokenLine(Graphics2D g, List<Point2D.Double> points) {
        Queue<Point2D.Double> pointsQueue = new LinkedBlockingQueue<>(points);
        Point2D.Double temp = pointsQueue.remove();

        while (!pointsQueue.isEmpty()) {
            g.draw(new Line2D.Double(temp, pointsQueue.peek()));
            temp = pointsQueue.remove();
        }
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}
