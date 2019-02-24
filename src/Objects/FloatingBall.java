package Objects;

import GameEngine.GameObjects.EllipseGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class FloatingBall extends EllipseGameObject {

	public FloatingBall(double centerX, double centerY, double radiusX, double radiusY) {
		super(centerX, centerY, radiusX, radiusY);
		setColor(Color.valueOf("#2FA9FFFF"));
		setMaximumVelocity(5);
		setFriction(0.02);
	}

	@Override
	public void update() {
		setAcceleration((getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? .1 : 0) + (getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -.1 : 0),
				(getGameEngine().getKeyboard()[KeyCode.UP.getCode()] ? -.1 : 0) + (getGameEngine().getKeyboard()[KeyCode.DOWN.getCode()] ? .1 : 0), 0);
//		setVelocity((getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? 5 : 0) + (getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -5 : 0),
//		(getGameEngine().getKeyboard()[KeyCode.UP.getCode()] ? -5 : 0) + (getGameEngine().getKeyboard()[KeyCode.DOWN.getCode()] ? 5 : 0), 0);

		if (getGameEngine().getKeyboard()[KeyCode.SPACE.getCode()])
			reset();

		super.update();
	}

}
