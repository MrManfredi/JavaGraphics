package kpi.manfredi.form;

public class AfinneDTO {
    private double Xx;
    private double Xy;
    private double Yx;
    private double Yy;
    private double Ox;
    private double Oy;

    public AfinneDTO(double xx, double xy, double yx, double yy, double ox, double oy) {
        Xx = xx;
        Xy = xy;
        Yx = yx;
        Yy = yy;
        Ox = ox;
        Oy = oy;
    }

    public double getXx() {
        return Xx;
    }

    public double getXy() {
        return Xy;
    }

    public double getYx() {
        return Yx;
    }

    public double getYy() {
        return Yy;
    }

    public double getOx() {
        return Ox;
    }

    public double getOy() {
        return Oy;
    }
}
