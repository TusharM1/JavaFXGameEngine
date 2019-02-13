package GameEngine.GameObjects;

import GameEngine.GameEngine;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class PolygonGameObject extends GameEngine.GameObject {

	public Polygon polygon;
	public Color color;

	public double centerX, centerY;
	public double[] xPoints, yPoints, allPoints;

	public PolygonGameObject(double locationX, double locationY, double[][] points, double centerX, double centerY) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.centerX = centerX;
		this.centerY = centerY;
		xPoints = Arrays.stream(points).map(doubles -> doubles[0]).mapToDouble(Double::doubleValue).toArray();
		yPoints = Arrays.stream(points).map(doubles -> doubles[1]).mapToDouble(Double::doubleValue).toArray();
		allPoints = Arrays.stream(points).flatMapToDouble(doubles -> DoubleStream.of(doubles[0], doubles[1])).toArray();
//		polygon = new Polygon(xPoints, yPoints, xPoints.length);
		polygon = new Polygon(allPoints);
		color = new Color(1,1,1,1);
	}

	@Override
	protected void drawObject(double canvasWidthCenter, double canvasHeightCenter) {
		GameEngine.graphicsContext.setFill(color);
//		GameEngine.graphicsContext.strokeRect(0, 0, GameEngine.canvas.getWidth(), GameEngine.canvas.getHeight());
		GameEngine.graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);
	}
}
