package kpi.manfredi;

import kpi.manfredi.form.AfinneDTO;
import kpi.manfredi.form.ProjectiveDTO;

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

    public static Point2D afinneTransform(Point2D point, AfinneDTO dto) {
        double x = point.getX();
        double y = point.getY();
        double newX = dto.getXx() * x + dto.getYx() * y + dto.getOx();
        double newY = dto.getXy() * x + dto.getYy() * y + dto.getOy();
        return new Point2D.Double(newX, newY);
    }

    public static Point2D projectiveTransform(Point2D point, ProjectiveDTO dto) {
        double x = point.getX();
        double y = point.getY();
        double newX = (dto.getXx() * dto.getWx() * x + dto.getYx() * dto.getWy() * y + dto.getOx() * dto.getWo()) / (dto.getWx() * x + dto.getWy() * y + dto.getWo());
        double newY = (dto.getXy() * dto.getWx() * x + dto.getYy() * dto.getWy() * y + dto.getOy() * dto.getWo()) / (dto.getWx() * x + dto.getWy() * y + dto.getWo());
        return new Point2D.Double(newX, newY);
    }

    public static double calculateDistance(Point2D point1, Point2D point2) {
        double x = point2.getX() - point1.getX();
        double y = point2.getY() - point1.getY();
        return Math.sqrt(x * x + y * y);
    }
}
