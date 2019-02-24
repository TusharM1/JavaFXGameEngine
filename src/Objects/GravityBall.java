package Objects;

import GameEngine.GameObjects.EllipseGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GravityBall extends EllipseGameObject {

	public GravityBall(double centerX, double centerY, double radiusX, double radiusY) {
		super(centerX, centerY, radiusX, radiusY);
		setColor(Color.valueOf("#2FA9FFFF"));
		setMaximumVelocity(20);
		setFriction(0.01);
	}

	@Override
	public void update() {
		setAcceleration((getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? .1 : 0) + (getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -.1 : 0), 0.25, 0);

		if (getGameEngine().getKeyboard()[KeyCode.SPACE.getCode()])
			reset();

		super.update();
	}

}
