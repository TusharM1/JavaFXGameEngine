package GameEngine.GameObjects;

import GameEngine.GameEngine;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class PolygonGameObject extends GameEngine.GameObject {

	public Polygon polygon;
	public Color color;

	public double centerX, centerY;
	public double[] xPoints, yPoints, allPoints;
	public int numberOfPoints;

	public PolygonGameObject(double locationX, double locationY, double[][] points, double centerX, double centerY) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.centerX = centerX;
		this.centerY = centerY;
		xPoints = Arrays.stream(points).map(doubles -> doubles[0]).mapToDouble(Double::doubleValue).toArray();
		yPoints = Arrays.stream(points).map(doubles -> doubles[1]).mapToDouble(Double::doubleValue).toArray();
//		System.out.println(Arrays.toString(xPoints));
//		System.out.println(Arrays.toString(yPoints));
		numberOfPoints = xPoints.length;
		allPoints = Arrays.stream(points).flatMapToDouble(doubles -> DoubleStream.of(doubles[0], doubles[1])).toArray();
//		polygon = new Polygon(xPoints, yPoints, xPoints.length);
		polygon = new Polygon(allPoints);
		color = new Color(1,1,1,1);
	}

	double lastLocationX, lastLocationY, lastRotation;

	@Override
	public void update() {
		lastLocationX = locationX;
		lastLocationY = locationY;
		lastRotation = rotation;

		super.update();

//		System.out.println(lastLocationX + " " + lastLocationY + " " + deltaX + " " + deltaY);

		polygon.setTranslateX(locationX);
		polygon.setTranslateY(locationY);
		polygon.setRotate(rotation);

//		System.out.println(polygon.getPoints().toString());

//		System.out.println(polygon.getTranslateX() + " " + polygon.getTranslateY());
	}

	@Override
	protected void drawObject() {
		GameEngine.graphicsContext.setFill(color);
//		GameEngine.graphicsContext.strokeRect(0, 0, GameEngine.canvas.getWidth(), GameEngine.canvas.getHeight());
//		GameEngine.graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);

//		System.out.println(Arrays.toString(allPoints));

		Translate translate = new Translate(locationX - lastLocationX, locationY - lastLocationY);
		translate.transform2DPoints(allPoints, 0, allPoints, 0, numberOfPoints);

		Rotate rotate = new Rotate(rotation - lastRotation, locationX, locationY);
		rotate.transform2DPoints(allPoints, 0, allPoints, 0, numberOfPoints);

		for (int i = 0; i < allPoints.length; i += 2)
			xPoints[i / 2] = allPoints[i];

		for (int i = 1; i < allPoints.length; i += 2)
			yPoints[i / 2] = allPoints[i];

		GameEngine.graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);
	}
}
