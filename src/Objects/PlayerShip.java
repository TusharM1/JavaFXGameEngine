package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.RectangleGameObject;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class PlayerShip extends RectangleGameObject {

	public PlayerShip(double locationX, double locationY, double width, double height) {
		super(locationX, locationY, width, height);
		color = Color.valueOf("#2FA9FFFF");

		maximumVelocity = 5;

		friction = .05;
		this.locationX = GameEngine.width / 2.0;
		this.locationY = GameEngine.height / 2.0;
	}

	@Override
	public void update() {
		rotationSpeed = (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -1 : 0) + (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 1 : 0);
		acceleration = (GameEngine.keyboard[KeyCode.UP.getCode()] ? .1 : 0) + (GameEngine.keyboard[KeyCode.DOWN.getCode()] ? -.1 : 0);

		if (GameEngine.keyboard[KeyCode.SPACE.getCode()]) {
			locationX = GameEngine.width / 2.0;
			locationY = GameEngine.height / 2.0;
			velocityX = velocityY = 0;
			rotation = 0;
		}

		super.update();
	}

}
