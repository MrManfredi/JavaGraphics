package kpi.manfredi;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Figure implements IFigure{
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

    public Figure() {
    }

    public double getW1() {
        return W1;
    }

    public void setW1(double w1) {
        W1 = w1;
    }

    public double getW2() {
        return W2;
    }

    public void setW2(double w2) {
        W2 = w2;
    }

    public double getH1() {
        return H1;
    }

    public void setH1(double h1) {
        H1 = h1;
    }

    public double getH2() {
        return H2;
    }

    public void setH2(double h2) {
        H2 = h2;
    }

    public double getH3() {
        return H3;
    }

    public void setH3(double h3) {
        H3 = h3;
    }

    public double getH4() {
        return H4;
    }

    public void setH4(double h4) {
        H4 = h4;
    }

    public double getH5() {
        return H5;
    }

    public void setH5(double h5) {
        H5 = h5;
    }

    public double getD1() {
        return D1;
    }

    public void setD1(double d1) {
        D1 = d1;
    }

    public double getR1() {
        return R1;
    }

    public void setR1(double r1) {
        R1 = r1;
    }

    public double getR2() {
        return R2;
    }

    public void setR2(double r2) {
        R2 = r2;
    }

    private void buildFigure() {

    }

    @Override
    public List<List<Point2D>> getContours() {
        Point2D.Double A = new Point2D.Double(0.0, 0.0);
        Point2D.Double B = new Point2D.Double(W2, 0.0);
        Point2D.Double O0 = new Point2D.Double(W2 / 2.0, 0.0);
        Point2D.Double C = new Point2D.Double(0.0, H4);
        Point2D.Double D = new Point2D.Double(B.getX(), C.getY());
        Point2D.Double E = new Point2D.Double(O0.getX() - R2, C.getY());
        Point2D.Double F = new Point2D.Double(O0.getX() + R2, C.getY());
        Point2D.Double G = new Point2D.Double(E.getX(), H5);
        Point2D.Double H = new Point2D.Double(F.getX(), G.getY());
        Point2D.Double O1 = new Point2D.Double(O0.getX(), G.getY() - H3);
//        Point2D.Double I1 = new Point2D.Double(O1.getX() - R1, O1.getY());
        Point2D.Double I = new Point2D.Double(O1.getX() - W1 / 2, O1.getY());
//        Point2D.Double J1 = new Point2D.Double(O1.getX() + R1, O1.getY());
        Point2D.Double J = new Point2D.Double(O1.getX() + W1 / 2, O1.getY());
        Point2D.Double O2 = new Point2D.Double(O0.getX(), G.getY());
        Point2D.Double L = new Point2D.Double(I.getX(), E.getY() + H2);
        Point2D.Double M = new Point2D.Double(J.getX(), L.getY());
        Point2D.Double K = new Point2D.Double(O0.getX(), L.getY() - H1);
        Point2D.Double O3 = new Point2D.Double(O0.getX(), O2.getY() + R2);

        List<Point2D> arcO1R1Points = Utils.getCirclePoints(O1, R1, Math.PI, Math.PI * 2); // name structure: figure_center_radius_type
        List<Point2D> contour1 = new ArrayList<>();
        contour1.addAll(Arrays.asList(J, M, K, L, I));
        contour1.addAll(arcO1R1Points);

        List<Point2D> arcO2R2Points2 = Utils.getCirclePoints(O2, R2, 0, Math.PI);
        List<Point2D> contour2 = new ArrayList<>();
        contour2.addAll(Arrays.asList(G, E, C, A, B, D, F, H));
        contour2.addAll(arcO2R2Points2);

        List<Point2D> circleO2D1Points = Utils.getCirclePoints(O2, D1 / 2, 0, Math.PI * 2);
        List<Point2D> contour3 = new ArrayList<>(circleO2D1Points);

        return new ArrayList<>(Arrays.asList(contour1, contour2, contour3));
    }
}
