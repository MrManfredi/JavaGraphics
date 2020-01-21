package kpi.manfredi;

import java.awt.geom.Point2D;
import java.util.List;

public interface IFigure {
    List<List<Point2D>> getContours();
    Point2D getPointInTheVicinity(Point2D point2D);
    Point2D getNextPoint(Point2D point2D);
    void moveUp(double num);
    void moveDown(double num);
    void moveRight(double num);
    void moveLeft(double num);
    void rotate(double x0, double y0, double angle);
    void toScale(double scalingFactor);
}
