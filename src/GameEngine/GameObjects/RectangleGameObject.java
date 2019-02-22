package GameEngine.GameObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class RectangleGameObject extends GameObject {

	// Consider changing this rectangle to keep consistency with other types of Game Objects
	private Rectangle hitBox;
	private Color color;

	private double width, height,
					widthCenter, heightCenter;

	public RectangleGameObject(double locationX, double locationY, double width, double height) {
		setLocation(locationX, locationY);
		setSize(width, height);
		setHitBox(new Rectangle(locationX, locationY, width, height));
		setColor(Color.WHITE);
		setObjectType("RectangleGameObject");
	}

	@Override
	public void update() {
		super.update();

		getHitBox().setX(getLocationX());
		getHitBox().setY(getLocationY());
		getHitBox().setRotate(getRotation());
	}

	@Override
	protected void drawObject() {
		getGraphicsContext().setFill(color);
		getGraphicsContext().transform(new Affine(new Rotate(getRotation(), getLocationX() + widthCenter, getLocationY() + heightCenter)));

		getGraphicsContext().setFill(color.darker());
		getGraphicsContext().fillRect(getLocationX(), getLocationY(), getWidth(), getHeight());

		getGraphicsContext().setFill(color.brighter());
		getGraphicsContext().fillRect(getLocationX(), getLocationY(), getWidth(), 10);
	}

	@Override
	public Rectangle getHitBox() { return hitBox; }
	public void setHitBox(Rectangle hitBox) { this.hitBox = hitBox; }

	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }

	public double getWidth() { return width; }
	public void setWidth(double width) { this.width = width; }
	public double getHeight() { return height; }
	public void setHeight(double height) { this.height = height; }
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
		this.widthCenter = width / 2;
		this.heightCenter = height / 2;
	}
	public double getWidthCenter() { return widthCenter; }
	public double getHeightCenter() { return heightCenter; }

}
