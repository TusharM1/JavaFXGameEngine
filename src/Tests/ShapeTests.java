package Tests;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ShapeTests {

	public static void main(String[] args) {

		Ellipse ellipse = new Ellipse();
		Rectangle rectangle = new Rectangle();
		ellipse.intersects(rectangle.getLayoutBounds());

		if (((Path) Shape.intersect(ellipse, rectangle)).getElements().size() > 0)
			System.out.println("intersection");

	}

}
