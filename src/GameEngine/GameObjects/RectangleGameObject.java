package GameEngine.GameObjects;

import GameEngine.GameEngine;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class RectangleGameObject extends GameEngine.GameObject {

	public Rectangle rectangle;
	public Color color;

	public double width, height;

	public RectangleGameObject(double locationX, double locationY, double width, double height) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.width = width;
		this.height = height;
		rectangle = new Rectangle(this.locationX, this.locationY, width, height);
		color = new Color(1,1,1,1);
		// x,y,w,h,r,c,v
	}

	@Override
	protected void drawObject(double canvasWidthCenter, double canvasHeightCenter) {
		GameEngine.graphicsContext.setFill(color);
//		GameEngine.graphicsContext.strokeRect(0, 0, GameEngine.canvas.getWidth(), GameEngine.canvas.getHeight());
		GameEngine.graphicsContext.setFill(color.darker());
		GameEngine.graphicsContext.fillRect(canvasWidthCenter - width / 2, canvasHeightCenter - height / 2, width, height);
		GameEngine.graphicsContext.setFill(color.brighter());
		GameEngine.graphicsContext.fillRect((GameEngine.canvas.getWidth() - width) / 2, (GameEngine.canvas.getHeight() - height) / 2, width, 10);
	}
}