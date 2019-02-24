package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.PolygonGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Starfish extends PolygonGameObject {

	public Starfish(double locationX, double locationY, double[][] points, double centerX, double centerY) {
		super(locationX, locationY, points, centerX, centerY);
		setColor(Color.valueOf("#2FA9FFFF"));
//		setMaximumVelocity(7.5);
//		setFriction(0.02);
//		color = Color.valueOf("#2FA9FFFF");
//
//		maximumVelocity = 7.5;
//		friction = .02;

//		this.locationX = GameEngine.width / 2.0;
//		this.locationY = GameEngine.height / 2.0;
	}

	@Override
	public void update() {
//		rotationSpeed = (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -2 : 0) + (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 2 : 0);
//		acceleration = (GameEngine.keyboard[KeyCode.UP.getCode()] ? .1 : 0) + (GameEngine.keyboard[KeyCode.DOWN.getCode()] ? -.1 : 0);
//		accelerationX = acceleration * Math.sin(Math.toRadians(rotation));
//		accelerationY = -acceleration * Math.cos(Math.toRadians(rotation));

//
////		velocityX = (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 5 : 0) + (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -5 : 0);
////		velocityY = (GameEngine.keyboard[KeyCode.UP.getCode()] ? -5 : 0) + (GameEngine.keyboard[KeyCode.DOWN.getCode()] ? 5 : 0);
//
//		accelerationX = (GameEngine.keyboard[KeyCode.D.getCode()] ? .1 : 0) + (GameEngine.keyboard[KeyCode.A.getCode()] ? -.1 : 0);
//		accelerationY = (GameEngine.keyboard[KeyCode.W.getCode()] ? -.1 : 0) + (GameEngine.keyboard[KeyCode.S.getCode()] ? .1 : 0);
//
//		if (GameEngine.keyboard[KeyCode.SPACE.getCode()]) {
//			locationX = GameEngine.width / 2.0;
//			locationY = GameEngine.height / 2.0;
//			velocityX = velocityY = 0;
////			deltaX = deltaY = deltaR = 0;
//			rotation = 0;
//		}

		setRotationSpeed((getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -2 : 0) + (getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? 2 : 0));
//		setAcceleration((getGameEngine().getKeyboard()[KeyCode.D.getCode()] ? .1 : 0) + (getGameEngine().getKeyboard()[KeyCode.A.getCode()] ? -.1 : 0),
//				(getGameEngine().getKeyboard()[KeyCode.W.getCode()] ? -.1 : 0) + (getGameEngine().getKeyboard()[KeyCode.S.getCode()] ? .1 : 0));
		setVelocity((getGameEngine().getKeyboard()[KeyCode.D.getCode()] ? 3 : 0) + (getGameEngine().getKeyboard()[KeyCode.A.getCode()] ? -3 : 0),
				(getGameEngine().getKeyboard()[KeyCode.W.getCode()] ? -3 : 0) + (getGameEngine().getKeyboard()[KeyCode.S.getCode()] ? 3 : 0), getRotation());

		super.update();
	}

}
