package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.RectangleGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class DragSquare extends RectangleGameObject {

	public DragSquare(double locationX, double locationY, double width, double height) {
		super(locationX, locationY, width, height);
	}

	double cursorInShapeX, cursorInShapeY;
	boolean selectedState;

	@Override
	public void update() {
		rotationSpeed = (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -2 : 0) + (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 2 : 0);
		MouseEvent mouseEvent;
		if ((mouseEvent = GameEngine.mouse) != null) {
			// if in square, save position
			// if in square and pressed, select
			// if selected and dragged, move
			// if clicked, deselect
			if (rectangle.contains(mouseEvent.getX(), mouseEvent.getY()) && mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
				cursorInShapeX = mouseEvent.getX() - locationX;
				cursorInShapeY = mouseEvent.getY() - locationY;
				selectedState = true;
			}
			if (selectedState && mouseEvent.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
				locationX = mouseEvent.getX() - cursorInShapeX;
				locationY = mouseEvent.getY() - cursorInShapeY;
				rectangle.setX(locationX);
				rectangle.setY(locationY);
			}
			if (!mouseEvent.isPrimaryButtonDown())
				selectedState = false;
		}
		super.update();
	}
}
