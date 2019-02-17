package GameEngine.GameObjects;

import GameEngine.GameEngine;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public abstract class RectangleGameObject extends GameEngine.GameObject {

	public Rectangle rectangle;
	public Color color;

	public double width, height, widthCenter, heightCenter;

	public RectangleGameObject(double locationX, double locationY, double width, double height) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.width = width;
		this.height = height;
		rectangle = new Rectangle(this.locationX, this.locationY, width, height);
		color = new Color(1,1,1,1);

		widthCenter = width / 2;
		heightCenter = height / 2;
	}

	@Override
	public void update() {
		super.update();

		rectangle.setX(locationX);
		rectangle.setY(locationY);
		rectangle.setRotate(rotation);
	}

	@Override
	protected void drawObject() {
		GameEngine.graphicsContext.setFill(color);
////		GameEngine.graphicsContext.strokeRect(0, 0, GameEngine.canvas.getWidth(), GameEngine.canvas.getHeight());
		GameEngine.graphicsContext.transform(new Affine(new Rotate(rotation, locationX + widthCenter, locationY + heightCenter)));
		GameEngine.graphicsContext.setFill(color.darker());
		GameEngine.graphicsContext.fillRect(locationX, locationY, width, height);

		GameEngine.graphicsContext.setFill(color.brighter());
		GameEngine.graphicsContext.fillRect(locationX, locationY, width, 10);
	}


}
