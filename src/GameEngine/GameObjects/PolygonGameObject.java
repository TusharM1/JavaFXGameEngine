package GameEngine.GameObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class PolygonGameObject extends GameObject {

	private Polygon hitBox;
	private Color color;

	private double centerX, centerY;
	private double lastLocationX, lastLocationY, lastRotation;
	
	private double[] xPoints, yPoints, allPoints;
	private int numberOfPoints;

	private Translate shapeTranslation;
	private Rotate shapeRotation;

	public PolygonGameObject(double locationX, double locationY, double[][] points, double centerX, double centerY) {
		setLocation(locationX, locationY);
		setCenter(centerX, centerY);

		xPoints = Arrays.stream(points).map(doubles -> doubles[0]).mapToDouble(Double::doubleValue).toArray();
		yPoints = Arrays.stream(points).map(doubles -> doubles[1]).mapToDouble(Double::doubleValue).toArray();
		allPoints = Arrays.stream(points).flatMapToDouble(doubles -> DoubleStream.of(doubles[0], doubles[1])).toArray();

		numberOfPoints = xPoints.length;

		setHitBox(new Polygon(allPoints));

		shapeTranslation = new Translate(locationX, locationY);
		shapeRotation = new Rotate(getRotation(), centerX, centerY);

		hitBox.getTransforms().addAll(shapeTranslation, shapeRotation);

		setColor(Color.WHITE);
		setObjectType("PolygonGameObject");
	}

	@Override
	public void update() {
		super.update();

		Translate translate = new Translate(getLocationX() - lastLocationX, getLocationY() - lastLocationY);
		Rotate rotate = new Rotate(getRotation() - lastRotation, getLocationX() + centerX, getLocationY() + centerY);

		shapeTranslation.setX(getLocationX());
		shapeTranslation.setY(getLocationY());
		shapeRotation.setAngle(getRotation());

		translate.transform2DPoints(allPoints, 0, allPoints, 0, numberOfPoints);
		rotate.transform2DPoints(allPoints, 0, allPoints, 0, numberOfPoints);

		for (int i = 0; i < allPoints.length; i += 2)
			xPoints[i / 2] = allPoints[i];

		for (int i = 1; i < allPoints.length; i += 2)
			yPoints[i / 2] = allPoints[i];

		lastLocationX = getLocationX();
		lastLocationY = getLocationY();
		lastRotation = getRotation();
	}

	@Override
	protected void drawObject() {
		getGraphicsContext().setFill(color);
		getGraphicsContext().fillPolygon(xPoints, yPoints, numberOfPoints);
	}

	@Override
	public Polygon getHitBox() { return hitBox; }
	public void setHitBox(Polygon hitBox) { this.hitBox = hitBox; }

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

//	public double getLastLocationX() { return lastLocationX; }
//	public void setLastLocationX(double lastLocationX) { this.lastLocationX = lastLocationX; }
//	public double getLastLocationY() { return lastLocationY; }
//	public void setLastLocationY(double lastLocationY) { this.lastLocationY = lastLocationY; }
//	public double getLastRotation() { return lastRotation; }
//	public void setLastRotation(double lastRotation) { this.lastRotation = lastRotation; }
//
//	public double[] getxPoints() { return xPoints; }
//	public void setxPoints(double[] xPoints) { this.xPoints = xPoints; }
//	public double[] getyPoints() { return yPoints; }
//	public void setyPoints(double[] yPoints) { this.yPoints = yPoints; }
//	public double[] getAllPoints() { return allPoints; }
//	public void setAllPoints(double[] allPoints) { this.allPoints = allPoints; }

	public int getNumberOfPoints() { return numberOfPoints; }

//	public Translate getShapeTranslation() { return shapeTranslation; }
//	public void setShapeTranslation(Translate shapeTranslation) { this.shapeTranslation = shapeTranslation; }
//
//	public Rotate getShapeRotation() { return shapeRotation; }
//	public void setShapeRotation(Rotate shapeRotation) { this.shapeRotation = shapeRotation; }

}
