package Objects;

import CoreEngine.GameObjects.RectangleGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SpaceShip extends RectangleGameObject {

//	public SpaceShip(double locationX, double locationY, double width, double height) {
//		super(locationX, locationY, width, height);
//		setColor(Color.valueOf("#2FA9FFFF"));
//		setMaximumVelocity(7.5);
//		setFriction(0.02);
//	}
//
//	public SpaceShip(double width, double height) {
//		this((getGameEngine().getWidth() - width) / 2, (getGameEngine().getHeight() - height) / 2, width, height);
//	}

	public SpaceShip(double locationX, double locationY, double width, double height) {
		super(width, height);
		setLocation(locationX, locationY);
	}

	public SpaceShip(double width, double height) {
		super(width, height);
		setColor(Color.valueOf("#2FA9FFFF"));
		setMaximumVelocity(7.5);
		setFriction(0.02);
	}

	@Override
	public void update() {
		setRotationSpeed((getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -2 : 0) + (getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? 2 : 0));
		setAcceleration((getGameEngine().getKeyboard()[KeyCode.UP.getCode()] ? .1 : 0) + (getGameEngine().getKeyboard()[KeyCode.DOWN.getCode()] ? -.1 : 0), getRotation());
//		setVelocity((getGameEngine().getKeyboard()[KeyCode.W.getCode()] ? -5 : 0) + (getGameEngine().getKeyboard()[KeyCode.S.getCode()] ? 5 : 0), getRotation());

		if (getGameEngine().getKeyboard()[KeyCode.SPACE.getCode()])
			reset();

		super.update();
	}

}
