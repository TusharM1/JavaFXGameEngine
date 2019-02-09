package GameEngine.GameObjects;

import javafx.scene.shape.Ellipse;

public class EllipseGameObject {

	Ellipse ellipse;

	public EllipseGameObject(double centerX, double centerY, double radiusX, double radiusY) {
		ellipse = new Ellipse(centerX, centerY, radiusX, radiusY);
	}
}
