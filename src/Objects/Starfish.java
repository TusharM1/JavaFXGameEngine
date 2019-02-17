package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.PolygonGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Starfish extends PolygonGameObject {

	public Starfish(double locationX, double locationY, double[][] points, double centerX, double centerY) {
		super(locationX, locationY, points, centerX, centerY);
		color = Color.valueOf("#2FA9FFFF");

		maximumVelocity = 7.5;
		friction = .02;

//		this.locationX = GameEngine.width / 2.0;
//		this.locationY = GameEngine.height / 2.0;
	}

	@Override
	public void update() {
		super.update();

		rotationSpeed = (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -2 : 0) + (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 2 : 0);
//		acceleration = (GameEngine.keyboard[KeyCode.UP.getCode()] ? .1 : 0) + (GameEngine.keyboard[KeyCode.DOWN.getCode()] ? -.1 : 0);
//		accelerationX = acceleration * Math.sin(Math.toRadians(rotation));
//		accelerationY = -acceleration * Math.cos(Math.toRadians(rotation));

//		velocityX = (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 5 : 0) + (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -5 : 0);
//		velocityY = (GameEngine.keyboard[KeyCode.UP.getCode()] ? -5 : 0) + (GameEngine.keyboard[KeyCode.DOWN.getCode()] ? 5 : 0);

		accelerationX = (GameEngine.keyboard[KeyCode.D.getCode()] ? .1 : 0) + (GameEngine.keyboard[KeyCode.A.getCode()] ? -.1 : 0);
		accelerationY = (GameEngine.keyboard[KeyCode.W.getCode()] ? -.1 : 0) + (GameEngine.keyboard[KeyCode.S.getCode()] ? .1 : 0);

		if (GameEngine.keyboard[KeyCode.SPACE.getCode()]) {
			locationX = GameEngine.width / 2.0;
			locationY = GameEngine.height / 2.0;
			velocityX = velocityY = 0;
//			deltaX = deltaY = deltaR = 0;
			rotation = 0;
		}
	}

}
