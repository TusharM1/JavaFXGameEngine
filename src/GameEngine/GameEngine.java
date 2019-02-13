package GameEngine;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class GameEngine {

	public IGameLoop game;

	public static int width;
	public static int height;

	public static boolean[] keyboard;

	public static Canvas canvas;
	public static GraphicsContext graphicsContext;

	Scene scene;
	GameLoop gameLoop;

	ArrayList<GameObject> renderQueue;

	public GameEngine(IGameLoop game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;

		gameLoop = new GameLoop();
		renderQueue = new ArrayList<>();

		keyboard = new boolean[65536];
	}

	public void init() {
		try {
			canvas = new Canvas(width, height);
			graphicsContext = canvas.getGraphicsContext2D();
		} catch (Exception e) {
			e.printStackTrace();
		}

		scene = new Scene(new Group(canvas));

		scene.widthProperty().addListener((observable, oldValue, newValue) -> {
			width = newValue.intValue();
			canvas.setWidth(width);
		});
		scene.heightProperty().addListener((observable, oldValue, newValue) -> {
			height = newValue.intValue();
			canvas.setHeight(height);
		});

		scene.addEventHandler(KeyEvent.ANY, event -> keyboard[event.getCode().getCode()] = event.getEventType().getName().equals("KEY_PRESSED"));
	}

	public Scene getScene() {
		return scene;
	}

	public ArrayList<GameObject> getRenderQueue() {
		return renderQueue;
	}

	public void start() {
		gameLoop.start();
	}

	private class GameLoop extends AnimationTimer {
		@Override
		public void handle(long now) {
			graphicsContext.setFill(Color.CYAN);
			graphicsContext.fillRect(0, 0, width, height);

			// update
			for (GameObject gameObject : renderQueue)
				gameObject.update();

			// logic
			game.updateFrame();

			// draw
			for (GameObject gameObject : renderQueue)
				gameObject.draw();
		}
	}

	public static abstract class GameObject {

		public double rotation, rotationSpeed,
				acceleration, accelerationX, accelerationY, friction,
				velocity, velocityX, velocityY, maximumVelocity,
				locationX, locationY,
				mass;

		public boolean isVisible;

		public GameObject() {
			isVisible = true;
		}

		public void update() {
			// TODO clean up this code
			rotation += rotationSpeed;

			velocityX += accelerationX;
			velocityY += accelerationY;

			velocity = Math.hypot(velocityX, velocityY);

			double frictionX = -friction * velocityX / velocity;
			double frictionY = -friction * velocityY / velocity;

			if (Math.abs(velocityX) > Math.abs(frictionX))
				velocityX += frictionX;
			else
				velocityX = 0;

			if (Math.abs(velocityY) > Math.abs(frictionY))
				velocityY += frictionY;
			else
				velocityY = 0;

			double maximumVelocityX = maximumVelocity * velocityX / velocity;
			double maximumVelocityY = maximumVelocity * velocityY / velocity;

			if (Math.abs(velocityX) > Math.abs(maximumVelocityX))
				velocityX = maximumVelocityX;
			if (Math.abs(velocityY) > Math.abs(maximumVelocityY))
				velocityY = maximumVelocityY;

			locationX += velocityX;
			locationY += velocityY;
		}

		public final void draw() {
			if (isVisible) {
				graphicsContext.save();

				double canvasWidth = canvas.getWidth(), canvasHeight = canvas.getHeight();
				double canvasWidthCenter = canvasWidth / 2, canvasHeightCenter = canvasHeight / 2;

				graphicsContext.translate(locationX, locationY);
				graphicsContext.transform(new Affine(new Rotate(rotation)));

				drawObject(canvasWidthCenter, canvasHeightCenter);

				graphicsContext.restore();
			}
		}

		abstract protected void drawObject(double canvasWidthCenter, double canvasHeightCenter);

		@Override
		public String toString() {
			return "Location X: " + locationX + "\n" +
					"Location Y: " + locationY + "\n" +
					"Velocity X: " + velocityX + "\n" +
					"Velocity Y: " + velocityY + "\n" +
					"Acceleration X: " + accelerationX + "\n" +
					"Acceleration Y: " + accelerationY + "\n" +
					"Rotation: " + rotation + "\n" +
					"Rotation Speed: " + rotationSpeed + "\n";
		}

//		public double getRotation() { return rotation; }
//		public void setRotation(double rotation) { this.rotation = rotation; }
//		public double getRotationSpeed() { return rotationSpeed; }
//		public void setRotationSpeed(double rotationSpeed) { this.rotationSpeed = rotationSpeed; }
//		public double getAccelerationX() { return accelerationX; }
//		public void setAccelerationX(double accelerationX) { this.accelerationX = accelerationX; }
//		public double getAccelerationY() { return accelerationY; }
//		public void setAccelerationY(double accelerationY) { this.accelerationY = accelerationY; }
//		public double getVelocityX() { return velocityX; }
//		public void setVelocityX(double velocityX) { this.velocityX = velocityX; }
//		public double getVelocityY() { return velocityY; }
//		public void setVelocityY(double velocityY) { this.velocityY = velocityY; }
//		public double getLocationX() { return locationX; }
//		public void setLocationX(double locationX) { this.locationX = locationX; }
//		public double getLocationY() { return locationY; }
//		public void setLocationY(double locationY) { this.locationY = locationY; }
//		public double getMass() { return mass; }
//		public void setMass(double mass) { this.mass = mass; }
//		public boolean isVisible() { return isVisible; }
//		public void setVisible(boolean visible) { isVisible = visible; }

	}

}
