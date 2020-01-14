package kpi.manfredi;

import kpi.manfredi.form.AfinneDTO;
import kpi.manfredi.form.ProjectiveDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DrawingPanel extends JPanel implements IDrawingPanel{
    private Figure figure;

    private String axisNameX = "X";
    private String axisNameY = "Y";
    private int cellSize = 20;
    private int axisSegmentSize = 40;
    private int padding = 40;
    private int labelPadding = 20;
    private boolean isGridVisible = false;

    private Point2D auxiliaryPoint = null;
    private boolean tangentLineCheckBox = false;
    private boolean perpendicularCheckBox = false;

    private double transformFactor = 0.0;
    private AfinneDTO afinneDTO;
    private ProjectiveDTO projectiveDTO;

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
        drawAxis(graphics2D);
        drawFigure(graphics2D);
        if (auxiliaryPoint != null) {
            if (tangentLineCheckBox) {
                drawTangentLine(graphics2D);
            }
            if (perpendicularCheckBox) {
                drawPerpendicular(graphics2D);
            }
        }
    }

    private void drawTangentLine(Graphics2D g) {
        g.setColor(Color.BLUE);
        Point2D nextPoint = figure.getNextPoint(auxiliaryPoint);
        if (nextPoint == null) {
            System.out.println("nextPoint is null");
            auxiliaryPoint = null;
            return;
        }
        Point2D vector = new Point2D.Double(
                nextPoint.getX() - auxiliaryPoint.getX(),
                nextPoint.getY() - auxiliaryPoint.getY());
        drawLineByVector(g, vector);
    }

    private void drawLineByVector(Graphics2D g, Point2D vector) {
        Point2D startPoint, endPoint;
        if (Math.abs(vector.getX()) < Math.abs(vector.getY())) {
            startPoint = getPointByY(0.0, vector);
            endPoint = getPointByY(getWorkspaceHeight(), vector);
        } else {
            startPoint = getPointByX(0.0, vector);
            endPoint = getPointByX(getWorkspaceWidth(), vector);
        }

        startPoint = adaptPoint(startPoint, vector);
        endPoint = adaptPoint(endPoint, vector);

        g.draw(new Line2D.Double(startPoint, endPoint));
    }

    private Point2D getPointByX(double x, Point2D vector) {
        double y = Utils.calculateY(auxiliaryPoint, vector, x);
        return new Point2D.Double(x + getFullPadding(), y + getFullPadding());
    }

    private Point2D getPointByY(double y, Point2D vector) {
        double x = Utils.calculateX(auxiliaryPoint, vector, y);
        return new Point2D.Double(x + getFullPadding(), y + getFullPadding());
    }

    private Point2D adaptPoint(Point2D point, Point2D vector) {
        int fullPadding = getFullPadding();
        if (point.getX() < fullPadding) {
            point = getPointByX(0.0 , vector);
        } else if (point.getX() > getWorkspaceWidth() + fullPadding) {
            point = getPointByX(getWorkspaceWidth(), vector);
        }
        if (point.getY() < fullPadding) {
            point = getPointByY(0.0, vector);
        } else if (point.getY() > getWorkspaceHeight() + fullPadding) {
            point = getPointByY(getWorkspaceHeight(), vector);
        }
        return point;
    }

    private void drawPerpendicular(Graphics2D g) {
        g.setColor(Color.BLUE);
        Point2D nextPoint = figure.getNextPoint(auxiliaryPoint);
        if (nextPoint == null) {
            System.out.println("nextPoint is null");
            auxiliaryPoint = null;
            return;
        }
        Point2D anotherPoint = getPerpendicularBasePoint(auxiliaryPoint, nextPoint);
        Point2D vector = new Point2D.Double(
                anotherPoint.getX() - auxiliaryPoint.getX(),
                anotherPoint.getY() - auxiliaryPoint.getY());
        drawLineByVector(g, vector);
    }

    private Point2D getPerpendicularBasePoint(Point2D basePoint, Point2D point1) {
        double x0 = basePoint.getX();
        double y0 = basePoint.getY();
        double x2 = x0 + 10;
        double y2 = ((point1.getX() - x0) * (x2 - x0)) / (y0 - point1.getY()) + y0;
        return new Point2D.Double(x2, y2);
    }

    private void drawGrid(Graphics2D g) {
        g.setStroke(new BasicStroke(1));
        g.setPaint(Color.GRAY);

        int fullPadding = getFullPadding();

        // parallel to the X axis
        for (int y = fullPadding; y <= fullPadding + getWorkspaceHeight(); y+= cellSize) {
            Point2D temp1 = new Point2D.Double((y - fullPadding) * transformFactor + fullPadding, y);
            Point2D temp2 = new Point2D.Double(((y - fullPadding) * transformFactor + fullPadding + getWorkspaceWidth()), y);
            g.draw(getTransformedLine(temp1, temp2));
        }

        // parallel to the Y axis
        for (int x = fullPadding; x <= fullPadding + getWorkspaceWidth(); x+= cellSize) {
            Point2D temp1 = new Point2D.Double(x, fullPadding);
            Point2D temp2 = new Point2D.Double(x + transformFactor * getWorkspaceHeight(),fullPadding + getWorkspaceHeight());
            g.draw(getTransformedLine(temp1, temp2));
        }
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
        g.setStroke(new BasicStroke(2));
        g.setPaint(Color.BLACK);
        g.setFont(new Font("Serif", Font.PLAIN, axisSegmentSize / 2));

        int fullPadding = getFullPadding();

        // axis
        g.draw(getTransformedLine(
                new Point2D.Double(fullPadding, fullPadding - 1),
                new Point2D.Double(fullPadding + getWorkspaceWidth(), fullPadding - 1)));      // X
        g.draw(getTransformedLine(
                new Point2D.Double(fullPadding - 1, fullPadding),
                new Point2D.Double(
                        getWorkspaceHeight() * transformFactor + fullPadding - 1,
                        fullPadding + getWorkspaceHeight())));     // Y

        int serifLength = 5;
        int indentationFromSerif = 2;

        // serifs on the X axis
        for (int x = fullPadding; x <= fullPadding + getWorkspaceWidth(); x+= axisSegmentSize) {
            g.draw(getTransformedLine(new Point2D.Double(x, fullPadding), new Point2D.Double(x, fullPadding - serifLength)));
        }

        // serifs on the Y axis
        for (int y = fullPadding; y <= fullPadding + getWorkspaceHeight(); y+= axisSegmentSize) {
            g.draw(getTransformedLine(
                    new Point2D.Double((y - fullPadding) * transformFactor + fullPadding , y),
                    new Point2D.Double((y - fullPadding) * transformFactor + fullPadding - serifLength, y)));
        }

        // numbering
        FontMetrics metrics = g.getFontMetrics();
        String origin = "O";
        int originWidth = metrics.stringWidth(origin);
        Point2D O = getTransformedDot(new Point2D.Double(fullPadding - serifLength - originWidth, fullPadding - serifLength));
        g.drawString(origin, (float) O.getX(), (float) O.getY()); // O

        // dimensions on the X axis
        for (int x = fullPadding + axisSegmentSize; x <= fullPadding + getWorkspaceWidth() - axisSegmentSize; x+= axisSegmentSize) {
            String label = String.valueOf(x - fullPadding);
            int labelWidth = metrics.stringWidth(label);
            Point2D temp = getTransformedDot(new Point2D.Double(x - labelWidth / 2.0, fullPadding - serifLength - indentationFromSerif));
            g.drawString(label , (float) temp.getX(), (float) temp.getY());
        }

        // dimensions on the Y axis
        for (int y = fullPadding + axisSegmentSize; y <= fullPadding + getWorkspaceHeight() - axisSegmentSize; y+= axisSegmentSize) {
            String label = String.valueOf(y - fullPadding);
            int labelWidth = metrics.stringWidth(label);
            Point2D temp = getTransformedDot(new Point2D.Double((y - fullPadding) * transformFactor + fullPadding - labelWidth - serifLength - indentationFromSerif, y));
            g.drawString(label , (float) temp.getX(), (float) temp.getY()); //  + g.getFont().getSize() / 2);
        }

        // axis name X
        int axisNameXWidth = metrics.stringWidth(axisNameX);
        Point2D X = getTransformedDot(new Point2D.Double(
                fullPadding + getWorkspaceWidth() - (getWorkspaceWidth() % axisSegmentSize) - axisNameXWidth / 2,
                fullPadding - serifLength - indentationFromSerif));
        g.drawString(axisNameX , (float) X.getX(), (float) X.getY());

        // axis name Y
        int axisNameYWidth = metrics.stringWidth(axisNameY);
        Point2D Y = getTransformedDot(new Point2D.Double(
                (float) getWorkspaceHeight() * (float) transformFactor + fullPadding - axisNameYWidth - serifLength - indentationFromSerif,
                fullPadding + getWorkspaceHeight() - (getWorkspaceHeight() % axisSegmentSize)));
        g.drawString(axisNameY,  (float) Y.getX(), (float) Y.getY());

//        // x arrow
//        g.drawLine(getWidth(), cellSize, getWidth() - cellSize / 2, (int) (cellSize * 0.66));
//        g.drawLine(getWidth(), cellSize, getWidth() - cellSize / 2, (int) (cellSize * 1.33));
//
//        // y arrow
//        g.drawLine(cellSize, getHeight(), (int) (cellSize * 0.66), getHeight() - cellSize / 2);
//        g.drawLine(cellSize, getHeight(), (int) (cellSize * 1.33), getHeight() - cellSize / 2);
    }

    private void drawContour(Graphics2D g, List<Point2D> contour) {
        if (contour == null || contour.isEmpty()) {
            return;
        }

        Queue<Point2D> pointsQueue = new LinkedBlockingQueue<>();
        for (Point2D point2D : contour) {
            Point2D temp = new Point2D.Double(point2D.getX() + getFullPadding(), point2D.getY() + getFullPadding());
            if (afinneDTO != null) {
                temp = Utils.afinneTransform(temp, afinneDTO);
            }
            if (projectiveDTO != null) {
                temp = Utils.projectiveTransform(temp, projectiveDTO);
            }
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

    public int getFullPadding() {
        return padding + labelPadding;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    private void drawFigure(Graphics2D g) {
        g.setStroke(new BasicStroke(3));
        g.setPaint(Color.RED);

        for (List<Point2D> contour : figure.getContours()) {
            drawContour(g, contour);
        }
    }

    @Override
    public void setAxis(String nameX, String nameY, int segmentSize) {
        this.axisNameX = nameX;
        this.axisNameY = nameY;
        this.axisSegmentSize = segmentSize;
    }

    @Override
    public void setGridVisible(boolean isVisible) {
        isGridVisible = isVisible;
    }

    @Override
    public void setGridSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public void setAuxiliaryPoint(Point2D auxiliaryPoint) {
        this.auxiliaryPoint = auxiliaryPoint;
    }

    public void setTangentLineCheckBox(boolean tangentLineCheckBox) {
        this.tangentLineCheckBox = tangentLineCheckBox;
    }

    public void setPerpendicularCheckBox(boolean perpendicularCheckBox) {
        this.perpendicularCheckBox = perpendicularCheckBox;
    }

    public Point2D getAuxiliaryPoint() {
        return auxiliaryPoint;
    }

    public boolean isTangentLineCheckBox() {
        return tangentLineCheckBox;
    }

    public boolean isPerpendicularCheckBox() {
        return perpendicularCheckBox;
    }

    public void changeTransformFactor(double transformFactor) {
        this.transformFactor += transformFactor;
    }

    public void setAfinneDTO(AfinneDTO afinneDTO) {
        this.afinneDTO = afinneDTO;
    }

    public void setProjectiveDTO(ProjectiveDTO projectiveDTO) {
        this.projectiveDTO = projectiveDTO;
    }

    private Point2D getTransformedDot(Point2D temp) {
        if (afinneDTO != null) {
            temp = Utils.afinneTransform(temp, afinneDTO);
        }
        if (projectiveDTO != null) {
            temp = Utils.projectiveTransform(temp, projectiveDTO);
        }
        return temp;
    }

    private Line2D getTransformedLine(Point2D temp1, Point2D temp2) {
        if (afinneDTO != null) {
            temp1 = Utils.afinneTransform(temp1, afinneDTO);
            temp2 = Utils.afinneTransform(temp2, afinneDTO);
        }
        if (projectiveDTO != null) {
            temp1 = Utils.projectiveTransform(temp1, projectiveDTO);
            temp2 = Utils.projectiveTransform(temp2, projectiveDTO);
        }
        return new Line2D.Double(temp1, temp2);
    }
}
