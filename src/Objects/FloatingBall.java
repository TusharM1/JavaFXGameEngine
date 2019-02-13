package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.EllipseGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class FloatingBall extends EllipseGameObject {

	public FloatingBall(double centerX, double centerY, double radiusX, double radiusY) {
		super(centerX, centerY, radiusX, radiusY);
		color = Color.valueOf("#2FA9FFFF");

		maximumVelocity = 5;
		friction = .02;

		this.locationX = GameEngine.width / 2.0;
		this.locationY = GameEngine.height / 2.0;
	}

	@Override
	public void update() {
		accelerationX = (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? .1 : 0) + (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -.1 : 0);
		accelerationY = (GameEngine.keyboard[KeyCode.UP.getCode()] ? -.1 : 0) + (GameEngine.keyboard[KeyCode.DOWN.getCode()] ? .1 : 0);

		if (GameEngine.keyboard[KeyCode.SPACE.getCode()]) {
			locationX = GameEngine.width / 2.0;
			locationY = GameEngine.height / 2.0;
			velocityX = velocityY = 0;
			rotation = 0;
		}

		super.update();
	}
}
