package Objects;

import GameEngine.GameObjects.RectangleGameObject;

public class PlayerShip extends RectangleGameObject {

	public PlayerShip(double locationX, double locationY, double width, double height) {
		super(locationX, locationY, width, height);
		velocityX = 1;
		velocityY = 1;
	}

	@Override
	public void update() {
		super.update();

	}
}
