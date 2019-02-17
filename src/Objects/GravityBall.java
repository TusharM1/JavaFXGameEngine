package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.EllipseGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GravityBall extends EllipseGameObject {

	public GravityBall(double centerX, double centerY, double radiusX, double radiusY) {
		super(centerX, centerY, radiusX, radiusY);

		color = Color.valueOf("#2FA9FFFF");

		maximumVelocity = 20;
		friction = .01;

		this.locationX = GameEngine.width / 2.0;
		this.locationY = GameEngine.height / 4.0;
	}

	@Override
	public void update() {
		accelerationX = (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? .1 : 0) + (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -.1 : 0);
		accelerationY = .25;

//		if (GameEngine.mouse.isPrimaryButtonDown() && GameEngine.mouse.)

		if (GameEngine.keyboard[KeyCode.SPACE.getCode()]) {
			locationX = GameEngine.width / 2.0;
			locationY = GameEngine.height / 4.0;
			velocityX = velocityY = 0;
			rotation = 0;
		}

		super.update();
	}

}
