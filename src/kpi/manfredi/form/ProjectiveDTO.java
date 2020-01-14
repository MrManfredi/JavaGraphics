package kpi.manfredi.form;

public class ProjectiveDTO {
    private double Xx;
    private double Xy;
    private double Yx;
    private double Yy;
    private double Ox;
    private double Oy;
    private double Wx;
    private double Wy;
    private double Wo;

    public ProjectiveDTO(double xx, double xy, double yx, double yy, double ox, double oy, double wx, double wy, double wo) {
        Xx = xx;
        Xy = xy;
        Yx = yx;
        Yy = yy;
        Ox = ox;
        Oy = oy;
        Wx = wx;
        Wy = wy;
        Wo = wo;
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

    public double getWx() {
        return Wx;
    }

    public double getWy() {
        return Wy;
    }

    public double getWo() {
        return Wo;
    }
}
