package Objects;

import CoreEngine.Dimension;
import CoreEngine.GameObjects.RectangleGameObject;
import CoreEngine.Location;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SpaceShip extends RectangleGameObject {

	public SpaceShip(Location location, Dimension dimension) {
		super(location, dimension);
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
