package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.RectangleGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class DragSquare extends RectangleGameObject {

	double cursorInShapeX, cursorInShapeY;
	boolean selectedState;

	public DragSquare(double locationX, double locationY, double width, double height) {
		super(locationX, locationY, width, height);
		color = Color.valueOf("#FFa500FF");

		name = "DragSquare";
	}

	@Override
	public void update() {
		rotationSpeed = (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -2 : 0) + (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 2 : 0);

		MouseEvent mouseEvent;
		if ((mouseEvent = GameEngine.mouse) != null) {
			if (((Path) Shape.intersect(GameEngine.mouseHitBox, rectangle)).getElements().size() > 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
				cursorInShapeX = mouseEvent.getX() - locationX;
				cursorInShapeY = mouseEvent.getY() - locationY;
				GameEngine.moveToFront(this);
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
