package kpi.manfredi;

public class Figure {
    boolean isFine = false;

    private int width;
    private int distanceToHole;
    private int holeDiameter;
    private int radiusOfRoundingBigger;
    private int radiusOfRoundingSmaller;
    private int height;

    public Figure() {
    }

    public Figure(Figure figure, double multiplier) {
        width = (int) (figure.getWidth() * multiplier);
        distanceToHole = (int) (figure.getDistanceToHole() * multiplier);
        holeDiameter = (int) (figure.getHoleDiameter() * multiplier);
        radiusOfRoundingBigger = (int) (figure.getRadiusOfRoundingBigger() * multiplier);
        radiusOfRoundingSmaller = (int) (figure.getRadiusOfRoundingSmaller() * multiplier);
        height = (int) (figure.getRadiusOfRoundingSmaller() * multiplier);
    }

    public boolean isFine() {
        return isFine;
    }

    public void setFine(boolean fine) {
        isFine = fine;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDistanceToHole() {
        return distanceToHole;
    }

    public void setDistanceToHole(int distanceToHole) {
        this.distanceToHole = distanceToHole;
    }

    public int getHoleDiameter() {
        return holeDiameter;
    }

    public void setHoleDiameter(int holeDiameter) {
        this.holeDiameter = holeDiameter;
    }

    public int getRadiusOfRoundingBigger() {
        return radiusOfRoundingBigger;
    }

    public void setRadiusOfRoundingBigger(int radiusOfRoundingBigger) {
        this.radiusOfRoundingBigger = radiusOfRoundingBigger;
    }

    public int getRadiusOfRoundingSmaller() {
        return radiusOfRoundingSmaller;
    }

    public void setRadiusOfRoundingSmaller(int radiusOfRoundingSmaller) {
        this.radiusOfRoundingSmaller = radiusOfRoundingSmaller;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
