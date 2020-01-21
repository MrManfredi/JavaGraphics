package kpi.manfredi;

import java.awt.geom.Point2D;
import java.util.List;

public abstract class Figure implements IFigure{
    protected List<List<Point2D>> contours;

    @Override
    public List<List<Point2D>> getContours() {
        return contours;
    }

    public Point2D getPointInTheVicinity(Point2D point2D) {
        int district = 5;
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                if (point.getX() - district < point2D.getX()
                        && point.getX() + district > point2D.getX()
                        && point.getY() - district < point2D.getY()
                        && point.getY() + district > point2D.getY()) {
                    return point;
                }
            }
        }
        return null;
    }

    public Point2D getNextPoint(Point2D point2D) {
        Point2D firstPoint = null;
        boolean isFinded = false;
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                if (isFinded) {
                    return point;
                }
                if (firstPoint == null) firstPoint = point;
                if (point == point2D){
                    isFinded = true;
                }
            }
            if (isFinded) {
                return firstPoint;
            }
        }
        return null;
    }

    public void moveUp(double num) {
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                point.setLocation(point.getX(), point.getY() - num);
            }
        }
    }

    public void moveDown(double num) {
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                point.setLocation(point.getX(), point.getY() + num);
            }
        }
    }

    public void moveRight(double num) {
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                point.setLocation(point.getX() + num, point.getY());
            }
        }
    }

    public void moveLeft(double num) {
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                point.setLocation(point.getX() - num, point.getY());
            }
        }
    }

    public void rotate(double x0, double y0, double angle) {
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                double x = point.getX();
                double y = point.getY();

                double angleRad = angle * Math.PI / 180;

                double newX = (x - x0) * Math.cos(angleRad) - (y - y0) * Math.sin(angleRad) + x0;
                double newY = (y - y0) * Math.cos(angleRad) + (x - x0) * Math.sin(angleRad) + y0;

                point.setLocation(newX, newY);
            }
        }
    }

    public void toScale(double scalingFactor) {
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                double x = point.getX();
                double y = point.getY();

                double newX = x * scalingFactor;
                double newY = y * scalingFactor;

                point.setLocation(newX, newY);
            }
        }
    }
}
