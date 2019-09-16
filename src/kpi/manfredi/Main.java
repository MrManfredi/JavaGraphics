package kpi.manfredi;

public class Main {
    public static void main(String[] args) {
        Figure figure = new Figure();
        Thread initializer = new Initializer(figure);
        initializer.start();

        Workplace workplace = new Workplace(figure);
        workplace.start();
    }
}
