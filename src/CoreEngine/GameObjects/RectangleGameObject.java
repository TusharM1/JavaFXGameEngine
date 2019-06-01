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

	public RectangleGameObject(Location location, Dimension dimension) {
		if (dimension != null)
			setSize(dimension);
		else
			this.dimension = new Dimension(100, 100);
		if (location != null)
			setLocation(location);
		setColor(Color.WHITE);
		setHitBox(new Rectangle(getLocationX() - getWidth() / 2, getLocationY() - getHeight() / 2, getWidth(), getHeight()));
		setObjectType("RectangleGameObject");
	}

	@Override
	public void update() {
		super.update();

		getHitBox().setX(getLocationX() - getWidth() / 2);
		getHitBox().setY(getLocationY() - getHeight() / 2);
		getHitBox().setRotate(getRotation());
	}

	@Override
	protected void drawObject() {
		getGraphicsContext().setFill(color);
		getGraphicsContext().transform(new Affine(new Rotate(getRotation(), getLocationX(), getLocationY())));

		getGraphicsContext().setFill(color.darker());
		getGraphicsContext().fillRect(getLocationX() - getWidth() / 2, getLocationY() - getHeight() / 2, getWidth(), getHeight());

		getGraphicsContext().setFill(color.brighter());
		getGraphicsContext().fillRect(getLocationX() - getWidth() / 2, getLocationY() - getHeight() / 2, getWidth(), 10);
	}

	@Override
	public void reset() {
		super.reset((getGameEngine().getWidth() + getWidth()) / 2, (getGameEngine().getHeight() + getHeight()) / 2);
	}

	@Override
	public Rectangle getHitBox() { return hitBox; }

	@Override public void onIntersectTopBound() { }
	@Override public void onIntersectBottomBound() { }
	@Override public void onIntersectLeftBound() { }
	@Override public void onIntersectRightBound() { }

	public void setHitBox(Rectangle hitBox) { this.hitBox = hitBox; }

	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }

	public double getWidth() { return dimension.getWidth(); }
	public double getHeight() { return dimension.getHeight(); }

	public void setWidth(double width) { dimension.setWidth(width); }
	public void setHeight(double height) { dimension.setHeight(height); }

	public void setSize(Dimension dimension) { this.dimension = dimension; }
	public void setSize(double width, double height) { dimension.setDimensions(width, height); }

}
