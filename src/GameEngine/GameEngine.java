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

	IGameLoop game;

	int width;
	int height;

	boolean[] keyboard;

	public static Canvas canvas;
	public static GraphicsContext graphicsContext;

	Scene scene;
	GameLoop gameLoop;

	ArrayList<GameObject> renderQueue;

	public GameEngine(IGameLoop game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;

		renderQueue = new ArrayList<>();
	}

	public void init() {
		try {
			canvas = new Canvas(width, height);
			graphicsContext = canvas.getGraphicsContext2D();
		} catch (Exception e) {
			e.printStackTrace();
		}

		scene = new Scene(new Group(canvas));

		gameLoop = new GameLoop();

		scene.widthProperty().addListener((observable, oldValue, newValue) -> {
			width = newValue.intValue();
			canvas.setWidth(width);
		});
		scene.heightProperty().addListener((observable, oldValue, newValue) -> {
			height = newValue.intValue();
			canvas.setHeight(height);
		});
	}

	public void createKeyListener() {
		keyboard = new boolean[65536];
		scene.addEventHandler(KeyEvent.ANY, event -> keyboard[event.getCode().getCode()] = event.getEventType().getName().equals("KEY_PRESSED"));
	}

	public Scene getScene() {
		return scene;
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
			game.updateFrame(renderQueue);

			// draw
			for (GameObject gameObject : renderQueue)
				gameObject.draw();
		}
	}

	public static abstract class GameObject {

		public double rotation, rotationSpeed;
		public double accelerationX, accelerationY;
		public double velocityX, velocityY;
		public double locationX, locationY;
		public double mass;

		public boolean isVisible;

		public GameObject () {
			isVisible = true;
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
