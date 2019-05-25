package CoreEngine;

public class Dimension {

    double width, height;

    public Dimension() {
        width = 0;
        height = 0;
    }

    public Dimension(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }

    public void setWidth(double width) { this.width = width; }
    public void setHeight(double height) { this.height = height; }

    public void setDimensions(double x, double y) {
        this.width = x;
        this.height = y;
    }

    public void setDimensions(Dimension dimension) {
        this.width = dimension.width;
        this.height = dimension.height;
    }

}
