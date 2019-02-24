package Objects;

import GameEngine.GameObjects.PolygonGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Starfish extends PolygonGameObject {

	public Starfish(double locationX, double locationY, double[][] points, double centerX, double centerY) {
		super(locationX, locationY, points, centerX, centerY);
		setColor(Color.valueOf("#2FA9FFFF"));
		setMaximumVelocity(7.5);
		setFriction(0.02);
	}

	@Override
	public void update() {
		setRotationSpeed((getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -2 : 0) + (getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? 2 : 0));
		setAcceleration((getGameEngine().getKeyboard()[KeyCode.D.getCode()] ? .1 : 0) + (getGameEngine().getKeyboard()[KeyCode.A.getCode()] ? -.1 : 0),
				(getGameEngine().getKeyboard()[KeyCode.W.getCode()] ? -.1 : 0) + (getGameEngine().getKeyboard()[KeyCode.S.getCode()] ? .1 : 0), getRotation());
//		setVelocity((getGameEngine().getKeyboard()[KeyCode.D.getCode()] ? 3 : 0) + (getGameEngine().getKeyboard()[KeyCode.A.getCode()] ? -3 : 0),
//				(getGameEngine().getKeyboard()[KeyCode.W.getCode()] ? -3 : 0) + (getGameEngine().getKeyboard()[KeyCode.S.getCode()] ? 3 : 0), getRotation());

		if (getGameEngine().getKeyboard()[KeyCode.SPACE.getCode()])
			reset();

		super.update();
	}

}
