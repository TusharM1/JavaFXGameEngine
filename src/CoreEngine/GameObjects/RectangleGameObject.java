package CoreEngine.GameObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class RectangleGameObject extends GameObject {

	// Consider changing this rectangle to keep consistency with other types of Game Objects
	private Rectangle hitBox;
	private Color color;

	private double width, height, widthCenter, heightCenter;

	public RectangleGameObject(double locationX, double locationY, double width, double height) {
		setSize(width, height);
		setLocation(locationX, locationY);
		setHitBox(new Rectangle(getLocationX(), getLocationY(), width, height));
		setColor(Color.WHITE);
		setObjectType("RectangleGameObject");
	}

	public RectangleGameObject(double width, double height) {
		this((getGameEngine().getWidth() - width) / 2, (getGameEngine().getHeight() - height) / 2, width, height);
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
	public void reset() {
		super.reset(getGameEngine().getWidth() / 2 - widthCenter, getGameEngine().getHeight() / 2 - heightCenter);
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

	@Override
	public double getCenterInCanvasX() { return getCanvas().getWidth() / 2 - widthCenter; }

	@Override
	public double getCenterInCanvasY() { return getCanvas().getHeight() / 2 - heightCenter; }

}