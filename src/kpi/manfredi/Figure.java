package kpi.manfredi;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Figure implements IFigure{
    private double x;
    private double y;
    private boolean isRotated;
    private boolean isChanged;

    private double W1;
    private double W2;
    private double H1;
    private double H2;
    private double H3;
    private double H4;
    private double H5;
    private double D1;
    private double R1;
    private double R2;

    private Point2D.Double A;
    private Point2D.Double B;
    private Point2D.Double O0;
    private Point2D.Double C;
    private Point2D.Double D;
    private Point2D.Double E;
    private Point2D.Double F;
    private Point2D.Double G;
    private Point2D.Double H;
    private Point2D.Double O1;
    private Point2D.Double I;
    private Point2D.Double J;
    private Point2D.Double O2;
    private Point2D.Double L;
    private Point2D.Double M;
    private Point2D.Double K;
    private Point2D.Double O3;

    private List<Point2D> contour1;
    private List<Point2D> contour2;
    private List<Point2D> contour3;
    private List<List<Point2D>> contours;

    private double transformFactor = 0.0;

    public Figure() {
        x = 0.0;
        y = 0.0;
        isChanged = true;
        isRotated = false;
    }

    public Figure(double x, double y) {
        this.x = x;
        this.y = y;
        isChanged = true;
        isRotated = false;
    }

    public double getW1() {
        return W1;
    }

    public void setW1(double w1) {
        W1 = w1;
        isChanged = true;
    }

    public double getW2() {
        return W2;
    }

    public void setW2(double w2) {
        W2 = w2;
        isChanged = true;
    }

    public double getH1() {
        return H1;
    }

    public void setH1(double h1) {
        H1 = h1;
        isChanged = true;
    }

    public double getH2() {
        return H2;
    }

    public void setH2(double h2) {
        H2 = h2;
        isChanged = true;
    }

    public double getH3() {
        return H3;
    }

    public void setH3(double h3) {
        H3 = h3;
        isChanged = true;
    }

    public double getH4() {
        return H4;
    }

    public void setH4(double h4) {
        H4 = h4;
        isChanged = true;
    }

    public double getH5() {
        return H5;
    }

    public void setH5(double h5) {
        H5 = h5;
        isChanged = true;
    }

    public double getD1() {
        return D1;
    }

    public void setD1(double d1) {
        D1 = d1;
        isChanged = true;
    }

    public double getR1() {
        return R1;
    }

    public void setR1(double r1) {
        R1 = r1;
        isChanged = true;
    }

    public double getR2() {
        return R2;
    }

    public void setR2(double r2) {
        R2 = r2;
        isChanged = true;
    }

    @Override
    public List<List<Point2D>> getContours() {
        if (!isRotated && isChanged) {
            calculatePoints();
            calculateContours();
            isChanged = false;
        }
        return new ArrayList<>(contours);
    }

    private void calculatePoints() {
        A = new Point2D.Double(x, y);
        B = new Point2D.Double(W2 + x, y);
        O0 = new Point2D.Double(W2 / 2.0 + x, y);
        C = new Point2D.Double(x, H4 + y);
        D = new Point2D.Double(B.getX(), C.getY());
        E = new Point2D.Double(O0.getX() - R2, C.getY());
        F = new Point2D.Double(O0.getX() + R2, C.getY());
        G = new Point2D.Double(E.getX(), H5 + y);
        H = new Point2D.Double(F.getX(), G.getY());
        O1 = new Point2D.Double(O0.getX(), G.getY() - H3);
        I = new Point2D.Double(O1.getX() - W1 / 2, O1.getY());
        J = new Point2D.Double(O1.getX() + W1 / 2, O1.getY());
        O2 = new Point2D.Double(O0.getX(), G.getY());
        L = new Point2D.Double(I.getX(), E.getY() + H2);
        M = new Point2D.Double(J.getX(), L.getY());
        K = new Point2D.Double(O0.getX(), L.getY() - H1);
        O3 = new Point2D.Double(O0.getX(), O2.getY() + R2);
    }

    private void calculateContours() {
        List<Point2D> arcO1R1Points = Utils.getCirclePoints(O1, R1, Math.PI, Math.PI * 2); // name structure: figure_center_radius_type
        contour1 = new ArrayList<>();
        contour1.addAll(Arrays.asList(J, M, K, L, I));
        contour1.addAll(arcO1R1Points);

        List<Point2D> arcO2R2Points2 = Utils.getCirclePoints(O2, R2, 0, Math.PI);
        contour2 = new ArrayList<>();
        contour2.addAll(Arrays.asList(G, E, C, A, B, D, F, H));
        contour2.addAll(arcO2R2Points2);

        List<Point2D> circleO2D1Points = Utils.getCirclePoints(O2, D1 / 2, 0, Math.PI * 2);
        contour3 = new ArrayList<>(circleO2D1Points);

        contours = new ArrayList<>(Arrays.asList(contour1, contour2, contour3));
    }

    public void moveUp(double num) {
        y -= num;
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                point.setLocation(point.getX(), point.getY() - num);
            }
        }
    }

    public void moveDown(double num) {
        y += num;
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                point.setLocation(point.getX(), point.getY() + num);
            }
        }
    }

    public void moveRight(double num) {
        x += num;
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                point.setLocation(point.getX() + num, point.getY());
            }
        }
    }

    public void moveLeft(double num) {
        x -= num;
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

        isRotated = true;
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
        W1 *= scalingFactor;
        W2 *= scalingFactor;
        H1 *= scalingFactor;
        H2 *= scalingFactor;
        H3 *= scalingFactor;
        H4 *= scalingFactor;
        H5 *= scalingFactor;
        D1 *= scalingFactor;
        R1 *= scalingFactor;
        R2 *= scalingFactor;
    }

    public Point2D getPoint(Point2D point2D) {
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

    public void transform(double transformFactor) {
        for (List<Point2D> contour : contours) {
            for (Point2D point : contour) {
                double x = point.getX();
                double y = point.getY();

                double newX = x + transformFactor * y;

                point.setLocation(newX, y);
            }
        }
    }
}
