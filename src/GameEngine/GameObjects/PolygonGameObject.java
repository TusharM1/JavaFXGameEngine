package GameEngine.GameObjects;

import GameEngine.GameEngine;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class PolygonGameObject extends GameEngine.GameObject {

	public Polygon polygon;
	public Color color;

	public double centerX, centerY;
	public double[] xPoints, yPoints, allPoints;
	public double lastLocationX, lastLocationY, lastRotation;
	public int numberOfPoints;

	public Translate shapeTranslation;
	public Rotate shapeRotation;

	// Possibly only have Polygon, Ellipse, Image and maybe Path Game Objects. Try making the rectangle Game Object into a polygon
	// Also try adding translation and rotation transformations to the original polygon points and remove the extra translation and rotation code.
	// But how did the collision detection work for rotated objects in the old code? figure this out.

	public PolygonGameObject(double locationX, double locationY, double[][] points, double centerX, double centerY) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.centerX = centerX;
		this.centerY = centerY;

		xPoints = Arrays.stream(points).map(doubles -> doubles[0]).mapToDouble(Double::doubleValue).toArray();
		yPoints = Arrays.stream(points).map(doubles -> doubles[1]).mapToDouble(Double::doubleValue).toArray();
		allPoints = Arrays.stream(points).flatMapToDouble(doubles -> DoubleStream.of(doubles[0], doubles[1])).toArray();

		numberOfPoints = xPoints.length;

		polygon = new Polygon(allPoints);
		shapeTranslation = new Translate(locationX, locationY);
		shapeRotation = new Rotate(rotation, centerX, centerY);
		polygon.getTransforms().addAll(shapeTranslation, shapeRotation);
		color = new Color(1,1,1,1);

		this.type = "PolygonGameObject";
	}

	@Override
	public void update() {
		super.update();

		Translate translate = new Translate(locationX - lastLocationX, locationY - lastLocationY);
		Rotate rotate = new Rotate(rotation - lastRotation, locationX + centerX, locationY + centerY);

		shapeTranslation.setX(locationX);
		shapeTranslation.setY(locationY);
		shapeRotation.setAngle(rotation);

		translate.transform2DPoints(allPoints, 0, allPoints, 0, numberOfPoints);
		rotate.transform2DPoints(allPoints, 0, allPoints, 0, numberOfPoints);

		for (int i = 0; i < allPoints.length; i += 2)
			xPoints[i / 2] = allPoints[i];

		for (int i = 1; i < allPoints.length; i += 2)
			yPoints[i / 2] = allPoints[i];

		lastLocationX = locationX;
		lastLocationY = locationY;
		lastRotation = rotation;
	}

	@Override
	protected void drawObject() {
		GameEngine.graphicsContext.setFill(color);

		GameEngine.graphicsContext.fillPolygon(xPoints, yPoints, numberOfPoints);
	}

	@Override
	public Shape getHitBox() {
		return polygon;
	}

}
