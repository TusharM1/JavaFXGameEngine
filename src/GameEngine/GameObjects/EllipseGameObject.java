package GameEngine.GameObjects;

import GameEngine.GameEngine;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class EllipseGameObject extends GameEngine.GameObject {

	public Ellipse ellipse;
	public Color color;

	public double radiusX, radiusY;

	public EllipseGameObject(double centerX, double centerY, double radiusX, double radiusY) {
		this.locationX = centerX;
		this.locationY = centerY;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
		ellipse = new Ellipse(centerX, centerY, radiusX, radiusY);
		color = new Color(1,1,1,1);
	}

	@Override
	protected void drawObject(double canvasWidthCenter, double canvasHeightCenter) {
		GameEngine.graphicsContext.setFill(color);
		GameEngine.graphicsContext.strokeRect(0, 0, GameEngine.canvas.getWidth(), GameEngine.canvas.getHeight());
		GameEngine.graphicsContext.setFill(color.darker());
		GameEngine.graphicsContext.fillOval(-radiusX, -radiusY, 2 * radiusX, 2 * radiusY);
		GameEngine.graphicsContext.setFill(color.brighter());
		GameEngine.graphicsContext.fillOval(-radiusX + 5, -radiusY + 5, 2 * radiusX - 10, 2 * radiusY - 10);

	}
}
