package kpi.manfredi;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Utils {
    public static List<Point2D> getCirclePoints(Point2D.Double center, double radius, double angleFrom, double angleTo) {
        List<Point2D> circlePoints = new ArrayList<>();
        double currentAngle = angleFrom;
        while (currentAngle <= angleTo + 0.001) {
            Point2D temp = new Point2D.Double();
            double xLocation = radius * Math.cos(currentAngle) + center.getX();
            double yLocation = radius * Math.sin(currentAngle) + center.getY();
            temp.setLocation(xLocation, yLocation);
            circlePoints.add(temp);
            currentAngle += Math.PI / 80;
        }
        return circlePoints;
    }

    public static double calculateX(Point2D point, Point2D vector, double y) {
        return (vector.getX() * (y - point.getY())) / vector.getY() + point.getX();
    }

    public static double calculateY(Point2D point, Point2D vector, double x) {
        return (vector.getY() * (x - point.getX())) / vector.getX() + point.getY();
    }
}
