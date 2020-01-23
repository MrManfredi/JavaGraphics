package kpi.manfredi;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class LemniscateOfBernoulli extends Figure implements IFigure{
    private double halfDistance;
    private double length;

    public LemniscateOfBernoulli(double halfDistance) {
        this.halfDistance = halfDistance;
        contours = calculateContours();
        length = calculateLength();
    }

    private double calculateLength() {
        double result = 0.0;
        for (List<Point2D> contour : contours) {
            Point2D prevPoint = null;
            for (Point2D point : contour) {
                if (prevPoint != null) {
                    result += Utils.calculateDistance(prevPoint, point);
                }
                prevPoint = point;
            }
            result += Utils.calculateDistance(prevPoint, contour.get(0));
        }
        return result;
    }

    private List<List<Point2D>> calculateContours() {
        List<List<Point2D>> contours = new ArrayList<>();
        List<Point2D> contour = new ArrayList<>();
        contour.add(new Point2D.Double(0.0, 0.0));
        calculatePoints(contour, Math.PI / 2.0, Math.PI * 3.0 / 2.0);
        contour.add(new Point2D.Double(0.0, 0.0));
        calculatePoints(contour, Math.PI * 3.0 / 2.0, Math.PI * 5.0 / 2.0);
        contour.add(new Point2D.Double(0.0, 0.0));
        contours.add(contour);
        return contours;
    }

    private void calculatePoints(List<Point2D> contour, double from, double to) { // ro^2 = 2 * halfDistance^2 * cos(2 * phi)
        double ro;
        double roSq;
        for (double phi = from; phi <= to; phi += 0.0125) {
            roSq = 2 * halfDistance * halfDistance * Math.cos(2 * phi);
            if (roSq < 0) continue;
            ro = Math.sqrt(roSq);
            contour.add(new Point2D.Double(ro * Math.cos(phi), ro * Math.sin(phi)));
        }
    }

    public double getLength() {
        return length;
    }
}
