package Objects.GameObjects;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public abstract class GameObject {

	static GraphicsContext graphicsContext;
	static Canvas canvas;

	double rotation, rotationSpeed;
	double accelerationX, accelerationY;
	double velocityX, velocityY;
	double locationX, locationY;
	double mass;

	boolean isVisible;

	public GameObject () {
		isVisible = true;
	}

	// FIXME change this logic to work with everything
	public static void setGraphicsContext(GraphicsContext graphicsContext) {
//		if (GameObject.graphicsContext != null || GameObject.canvas != null)
//			throw new Exception("GameObject.setGraphicsContext can only be called once and must be called before creating any GameObjects");
		GameObject.graphicsContext = graphicsContext;
		GameObject.canvas = graphicsContext.getCanvas();
	}

	public static void set(GraphicsContext graphicsContext) {
		GameObject.graphicsContext = graphicsContext;
		GameObject.canvas = graphicsContext.getCanvas();
	}

	public void update() {
		rotation += rotationSpeed;
		velocityX += accelerationX;
		velocityY += accelerationY;
		locationX += velocityX;
		locationY += velocityY;
	}

	public final void draw() {
		if (isVisible) {
			graphicsContext.save();

			double canvasWidth = canvas.getWidth(), canvasHeight = canvas.getHeight();
			double canvasWidthCenter = canvasWidth / 2, canvasHeightCenter = canvasHeight / 2;

			graphicsContext.translate(locationX - canvasWidthCenter, locationY - canvasHeightCenter);
			graphicsContext.transform(new Affine(new Rotate(rotation, canvasWidthCenter, canvasHeightCenter)));

			drawObject(canvasWidthCenter, canvasHeightCenter);

			graphicsContext.restore();
		}
	}

	abstract protected void drawObject(double canvasWidthCenter, double canvasHeightCenter);

	public double getRotation() { return rotation; }
	public void setRotation(double rotation) { this.rotation = rotation; }
	public double getRotationSpeed() { return rotationSpeed; }
	public void setRotationSpeed(double rotationSpeed) { this.rotationSpeed = rotationSpeed; }
	public double getAccelerationX() { return accelerationX; }
	public void setAccelerationX(double accelerationX) { this.accelerationX = accelerationX; }
	public double getAccelerationY() { return accelerationY; }
	public void setAccelerationY(double accelerationY) { this.accelerationY = accelerationY; }
	public double getVelocityX() { return velocityX; }
	public void setVelocityX(double velocityX) { this.velocityX = velocityX; }
	public double getVelocityY() { return velocityY; }
	public void setVelocityY(double velocityY) { this.velocityY = velocityY; }
	public double getLocationX() { return locationX; }
	public void setLocationX(double locationX) { this.locationX = locationX; }
	public double getLocationY() { return locationY; }
	public void setLocationY(double locationY) { this.locationY = locationY; }
	public double getMass() { return mass; }
	public void setMass(double mass) { this.mass = mass; }
	public boolean isVisible() { return isVisible; }
	public void setVisible(boolean visible) { isVisible = visible; }

}
