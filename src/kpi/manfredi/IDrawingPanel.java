package kpi.manfredi;

public interface IDrawingPanel {
    void setAxis(String nameX, String nameY, int segmentSize);
    void setGridVisible(boolean isVisible);
    void setGridSize(int cellSize);
}
