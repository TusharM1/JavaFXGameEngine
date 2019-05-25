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

//	private double centerX, centerY,
//					radiusX, radiusY,
//					diameterX, diameterY;

	private Dimension dimension;

	public EllipseGameObject(Location location, Dimension dimension) {
		if (dimension != null)
			setRadius(dimension);
		else
			setRadius(new Dimension(100, 100));
		if (location != null) ;
			setCenter(location);
		else
			setCenter(getGameEngine().getWidth() / 2 - radiusX, getGameEngine().getHeight() / 2 - radiusY, radiusX, radiusY);
//		setCenter(centerX, centerY);
		setRadius(radiusX, radiusY);
		setLocation(centerX - radiusX, centerY - radiusY);
		// Check if this is the correct hit box (it doesn't seem like it)
		setHitBox(new Ellipse(centerX, centerY, radiusX, radiusY));
		setColor(Color.WHITE);
		setObjectType("EllipseGameObject");
	}

	public EllipseGameObject(double radiusX, double radiusY) {
		this(getGameEngine().getWidth() / 2 - radiusX, getGameEngine().getHeight() / 2 - radiusY, radiusX, radiusY);
	}


//	public EllipseGameObject(double centerX, double centerY, double radiusX, double radiusY) {
//		setCenter(centerX, centerY);
//		setRadius(radiusX, radiusY);
//		setLocation(centerX - radiusX, centerY - radiusY);
//		// Check if this is the correct hit box (it doesn't seem like it)
//		setHitBox(new Ellipse(centerX, centerY, radiusX, radiusY));
//		setColor(Color.WHITE);
//		setObjectType("EllipseGameObject");
//	}
//
//	public EllipseGameObject(double radiusX, double radiusY) {
//		this(getGameEngine().getWidth() / 2 - radiusX, getGameEngine().getHeight() / 2 - radiusY, radiusX, radiusY);
//	}

	@Override
	public void update() {
		super.update();

		setCenter(getLocationX() + radiusX, getLocationY() + radiusY);

		getHitBox().setCenterX(centerX);
		getHitBox().setCenterY(centerY);
		getHitBox().setRotate(getRotation());
	}

	@Override
	protected void drawObject() {
		getGraphicsContext().setFill(color);
		getGraphicsContext().transform(new Affine(new Rotate(getRotation(), centerX, centerY)));

		getGraphicsContext().setFill(color.darker());
		getGraphicsContext().fillOval(getLocationX(), getLocationY(),  diameterX,  diameterY);

		getGraphicsContext().setFill(color.brighter());
		getGraphicsContext().fillOval(getLocationX() + 5, getLocationY() + 5, diameterX - 10, diameterY - 10);
	}

	@Override
	public Ellipse getHitBox() { return hitBox; }
	public void setHitBox(Ellipse hitBox) { this.hitBox = hitBox; }

	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }

	public double getCenterX() { return centerX; }
	public void setCenterX(double centerX) { this.centerX = centerX; }
	public double getCenterY() { return centerY; }
	public void setCenterY(double centerY) { this.centerY = centerY; }
	public void setCenter(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
	}

	public double getRadiusX() { return radiusX; }
	public void setRadiusX(double radiusX) {
		this.radiusX = radiusX;
		this.diameterX = 2 * radiusX;
	}
	public double getRadiusY() { return radiusY; }
	public void setRadiusY(double radiusY) {
		this.radiusY = radiusY;
		this.diameterY = 2 * radiusY;
	}
	public void setRadius(double radiusX, double radiusY) {
		this.radiusX = radiusX;
		this.radiusY = radiusY;
		this.diameterX = 2 * radiusX;
		this.diameterY = 2 * radiusY;
	}
	public void setRadius(double radius) { this.radiusX = this.radiusY = radius; }

	public double getDiameterX() { return diameterX; }
	public void setDiameterX(double diameterX) {
		this.diameterX = diameterX;
		this.radiusX = diameterX / 2;
	}
	public double getDiameterY() { return diameterY; }
	public void setDiameterY(double diameterY) {
		this.diameterY = diameterY;
		this.radiusY = diameterY / 2;
	}
	public void setDiameter(double diameterX, double diameterY) {
		this.diameterX = diameterX;
		this.diameterY = diameterY;
		this.radiusX = diameterX / 2;
		this.radiusY = diameterY / 2;
	}
	public void setDiameter(double diameter) { this.diameterX = this.diameterY = diameter; }
}
