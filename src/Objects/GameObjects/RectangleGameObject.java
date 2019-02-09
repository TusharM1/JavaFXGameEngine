package Objects.GameObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class RectangleGameObject extends GameObject {

	Rectangle rectangle;
	Color color;

	double width, height;

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
		graphicsContext.setFill(color);
//		graphicsContext.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphicsContext.fillRect(canvasWidthCenter - width / 2, canvasHeightCenter - height / 2, width, height);
	}
}
