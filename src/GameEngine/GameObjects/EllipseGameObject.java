package GameEngine.GameObjects;

import GameEngine.GameEngine;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class EllipseGameObject extends GameEngine.GameObject {

	public Ellipse ellipse;
	public Color color;

	public double centerX, centerY, radiusX, radiusY, diameterX, diameterY;

	public EllipseGameObject(double centerX, double centerY, double radiusX, double radiusY) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
		this.diameterX = 2 * radiusX;
		this.diameterY = 2 * radiusY;
		this.locationX = centerX - radiusX;
		this.locationY = centerY - radiusY;
		ellipse = new Ellipse(centerX, centerY, radiusX, radiusY);
		color = new Color(1,1,1,1);
	}

	@Override
	public void update() {
		super.update();

		centerX = locationX + radiusX;
		centerY = locationY + radiusY;

		ellipse.setCenterX(centerX);
		ellipse.setCenterY(centerY);
		ellipse.setRotate(rotation);
	}

	@Override
	protected void drawObject() {
		GameEngine.graphicsContext.setFill(color);
//		GameEngine.graphicsContext.strokeRect(0, 0, GameEngine.canvas.getWidth(), GameEngine.canvas.getHeight());
		GameEngine.graphicsContext.transform(new Affine(new Rotate(rotation, centerX, centerY)));

		GameEngine.graphicsContext.setFill(color.darker());
		GameEngine.graphicsContext.fillOval(locationX, locationY,  diameterX,  diameterY);

		GameEngine.graphicsContext.setFill(color.brighter());
		GameEngine.graphicsContext.fillOval(locationX + 5, locationY + 5, diameterX - 10, diameterY - 10);
	}

	@Override
	public Node getNode() {
		return ellipse;
	}
}
