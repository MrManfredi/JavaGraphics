package kpi.manfredi;

public class Figure {
    private boolean isFine = false;

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

    public Figure(Figure figure, double multiplier) {
        W1 = figure.getW1() * multiplier;
        W2 = figure.getW2() * multiplier;
        H1 = figure.getH1() * multiplier;
        H2 = figure.getH2() * multiplier;
        H3 = figure.getH3() * multiplier;
        H4 = figure.getH4() * multiplier;
        H5 = figure.getH5() * multiplier;
        D1 = figure.getD1() * multiplier;
        R1 = figure.getR1() * multiplier;
        R2 = figure.getR2() * multiplier;
    }

    public boolean isFine() {
        return isFine;
    }

    public void setFine(boolean fine) {
        isFine = fine;
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
}
