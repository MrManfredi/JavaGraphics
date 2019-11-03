package kpi.manfredi;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Contour implements IContour{
    private List<Point2D> points;

    public Contour() {
        points = new ArrayList<>();
    }

    public Contour(List<Point2D> points) {
        this.points = points;
    }

    public void addPoint(Point2D point) {
        this.points.add(point);
    }

    public void addPoints(Collection<Point2D> points) {
        this.points.addAll(points);
    }

    @Override
    public List<Point2D> getPoints() {
        return points;
    }
}
