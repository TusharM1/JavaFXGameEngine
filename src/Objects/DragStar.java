package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.PolygonGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class DragStar extends PolygonGameObject {

	public DragStar(double locationX, double locationY, double[][] points, double centerX, double centerY) {
		super(locationX, locationY, points, centerX, centerY);
		color = Color.valueOf("#2FA9FFFF");
	}

	double cursorInShapeX, cursorInShapeY;
	boolean selectedState;

	@Override
	public void update() {
		rotationSpeed = (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -2 : 0) + (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 2 : 0);

		MouseEvent mouseEvent;
		if ((mouseEvent = GameEngine.mouse) != null) {
			if (((Path) Shape.intersect(GameEngine.mouseHitBox, polygon)).getElements().size() > 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
				cursorInShapeX = mouseEvent.getX() - locationX;
				cursorInShapeY = mouseEvent.getY() - locationY;
				selectedState = true;
			}
			if (selectedState && mouseEvent.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
				locationX = mouseEvent.getX() - cursorInShapeX;
				locationY = mouseEvent.getY() - cursorInShapeY;
			}
			if (!mouseEvent.isPrimaryButtonDown())
				selectedState = false;
		}

		super.update();
	}

}
