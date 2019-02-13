package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.PolygonGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GravityTriangle extends PolygonGameObject {

	public GravityTriangle(double locationX, double locationY, double[][] points, double centerX, double centerY) {
		super(locationX, locationY, points, centerX, centerY);
		color = Color.valueOf("#2FA9FFFF");

		maximumVelocity = 7.5;
		friction = .02;

//		this.locationX = GameEngine.width / 2.0;
//		this.locationY = GameEngine.height / 2.0;
	}

	@Override
	public void update() {
		rotationSpeed = (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -2 : 0) + (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 2 : 0);
		acceleration = (GameEngine.keyboard[KeyCode.UP.getCode()] ? .1 : 0) + (GameEngine.keyboard[KeyCode.DOWN.getCode()] ? -.1 : 0);
		accelerationX = acceleration * Math.sin(Math.toRadians(rotation));
		accelerationY = -acceleration * Math.cos(Math.toRadians(rotation));

		if (GameEngine.keyboard[KeyCode.SPACE.getCode()]) {
			locationX = GameEngine.width / 2.0;
			locationY = GameEngine.height / 2.0;
			velocityX = velocityY = 0;
			rotation = 0;
		}

		super.update();
	}

}
