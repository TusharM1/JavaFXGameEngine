package CoreEngine.GameObjects;

import CoreEngine.Dimension;
import CoreEngine.Location;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class EllipseGameObject extends GameObject {

	private Ellipse hitBox;
	private Color color;

	private Dimension dimension;

	public EllipseGameObject(Location location, Dimension dimension) {
		if (dimension != null)
			setSize(dimension);
		else
			setSize(new Dimension(100, 100));
		if (location != null)
			setLocation(location);
		setHitBox(new Ellipse(getLocationX(), getLocationY(), getWidth() / 2, getHeight() / 2));
		setColor(Color.WHITE);
		setObjectType("EllipseGameObject");
	}

	@Override
	public void update() {
		super.update();

		hitBox.setCenterX(getLocationX());
		hitBox.setCenterY(getLocationY());
		hitBox.setRotate(getRotation());

		resolveCollisions();

//		if (intersectsBottomBound()) {
//			setLocationY(getCanvas().getHeight() - getHeight() / 2);
//			hitBox.setCenterY(getLocationY());
//		}

	}

	@Override
	protected void drawObject() {
		getGraphicsContext().setFill(color);
		getGraphicsContext().transform(new Affine(new Rotate(getRotation(), getLocationX(), getLocationY())));

		getGraphicsContext().setFill(color.darker());
		getGraphicsContext().fillOval(getLocationX() - getWidth() / 2, getLocationY() - getHeight() / 2,  getWidth(),  getHeight());

		getGraphicsContext().setFill(color.brighter());
		getGraphicsContext().fillOval(getLocationX() - getWidth() / 2 + 5, getLocationY() - getHeight() / 2 + 5, getWidth() - 10, getHeight() - 10);
	}


	@Override
	public Ellipse getHitBox() { return hitBox; }

	@Override public void onIntersectTopBound() { }
	@Override public void onIntersectBottomBound() { }
	@Override public void onIntersectLeftBound() { }
	@Override public void onIntersectRightBound() { }

	public void setHitBox(Ellipse hitBox) { this.hitBox = hitBox; }

	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }

	public double getWidth() { return dimension.getWidth(); }
	public double getHeight() { return dimension.getHeight(); }

	public void setWidth(double width) { dimension.setWidth(width); }
	public void setHeight(double height) { dimension.setHeight(height); }

	public void setSize(Dimension dimension) { this.dimension = dimension; }
	public void setSize(double width, double height) { dimension.setDimensions(width, height); }

}
