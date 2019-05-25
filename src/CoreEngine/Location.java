package CoreEngine;

import CoreEngine.Utilities.Vector2D;

public class Location {

    double x, y;

    public Location() {
        x = 0;
        y = 0;
    }

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(Location location) {
        this.x = location.x;
        this.y = location.y;
    }

    public void adjustLocation(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public void adjustLocation(Vector2D vector2D) {
        this.x += vector2D.getComponentX();
        this.y += vector2D.getComponentY();
    }

}
