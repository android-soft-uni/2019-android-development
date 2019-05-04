package softuni_demo;

public class Circle {
    private int radius;
    private int centerX;
    private int centerY;

    public Circle(int rad) {
        this.radius = rad;
    }

    double getArea() {
        return Math.PI * radius * radius;
    }

    double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    public int getRadius() {
        return radius;
    }
}
