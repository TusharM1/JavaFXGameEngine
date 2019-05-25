package CoreEngine.GameObjects;

import CoreEngine.Dimension;
import CoreEngine.Location;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class RectangleGameObject extends GameObject {

	// Consider changing this rectangle to keep consistency with other types of Game Objects
	private Rectangle hitBox;
	private Color color;

	private Dimension dimension;

//	private double width, height, widthCenter, heightCenter;

	public RectangleGameObject(Location location, Dimension dimension) {
		if (dimension != null)
			setSize(dimension);
		else
			this.dimension = new Dimension(100, 100);
		if (location != null)
			setLocation(location);
		else
			this.location = new Location(getGameEngine().getWidth() + getWidth() / 2, (getGameEngine().getHeight() + getHeight()) / 2);
		setColor(Color.WHITE);
		setHitBox(new Rectangle(getLocationX(), getLocationY(), getWidth(), getHeight()));
		setObjectType("RectangleGameObject");
	}

//	public RectangleGameObject(Location location, Dimension dimension) {
//		setSize(dimension);
//		setLocation(location);
//	}
//
//	public RectangleGameObject(Location location) {
//		setSize(new Dimension(100, 100));
//		setLocation(location);
//	}
//
//	public RectangleGameObject(Dimension dimension) {
//		setSize(dimension);
//		setLocation(new Location((getGameEngine().getWidth() + getWidth()) / 2, (getGameEngine().getHeight() + getHeight()) / 2));
//	}
//
//	public RectangleGameObject() {
//		setSize(new Dimension(100, 100));
//		setLocation(new Location((getGameEngine().getWidth() + getWidth()) / 2, (getGameEngine().getHeight() + getHeight()) / 2));
//	}
//
//	@Override
//	public void initialize() {
//		setColor(Color.WHITE);
//		setHitBox(new Rectangle(getLocationX(), getLocationY(), getWidth(), getHeight()));
//		setObjectType("RectangleGameObject");
//	}

//
//	public RectangleGameObject(double locationX, double locationY, double width, double height) {
//		setSize(width, height);
//		setLocation(locationX, locationY);
//		setHitBox(new Rectangle(getLocationX(), getLocationY(), width, height));
//		setColor(Color.WHITE);
//		setObjectType("RectangleGameObject");
//	}

//	public RectangleGameObject(double width, double height) {
//		this((getGameEngine().getWidth() - width) / 2, (getGameEngine().getHeight() - height) / 2, width, height);
//	}

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
		getGraphicsContext().transform(new Affine(new Rotate(getRotation(), getLocationX() + getWidth() / 2, getLocationY() + getHeight() / 2)));

		getGraphicsContext().setFill(color.darker());
		getGraphicsContext().fillRect(getLocationX(), getLocationY(), getWidth(), getHeight());

		getGraphicsContext().setFill(color.brighter());
		getGraphicsContext().fillRect(getLocationX(), getLocationY(), getWidth(), 10);
	}

	@Override
	public void reset() {
		super.reset((getGameEngine().getWidth() + getWidth()) / 2, (getGameEngine().getHeight() + getHeight()) / 2);
	}

	@Override
	public Rectangle getHitBox() { return hitBox; }
	public void setHitBox(Rectangle hitBox) { this.hitBox = hitBox; }

	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }

	public double getWidth() { return dimension.getWidth(); }
	public double getHeight() { return dimension.getHeight(); }
	public Dimension getDimension() { return dimension; }

	public void setSize(Dimension dimension) { this.dimension = dimension; }
	public void setSize(double width, double height) {
		if (dimension == null)
			dimension = new Dimension(width, height);
		else
			dimension.setDimensions(width, height);
	}

//	public double getWidth() { return width; }
//	public void setWidth(double width) { this.width = width; }
//	public double getHeight() { return height; }
//	public void setHeight(double height) { this.height = height; }
//	public void setSize(double width, double height) {
//		this.width = width;
//		this.height = height;
//		this.widthCenter = width / 2;
//		this.heightCenter = height / 2;
//	}
//	public double getWidthCenter() { return widthCenter; }
//	public double getHeightCenter() { return heightCenter; }

//	@Override
//	public double getCenterInCanvasX() { return getCanvas().getWidth() / 2 - widthCenter; }
//
//	@Override
//	public double getCenterInCanvasY() { return getCanvas().getHeight() / 2 - heightCenter; }

}
