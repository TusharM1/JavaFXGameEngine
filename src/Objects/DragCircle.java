package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.EllipseGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class DragCircle extends EllipseGameObject {

	double cursorInShapeX, cursorInShapeY;
	boolean selectedState;

	public DragCircle(double centerX, double centerY, double radiusX, double radiusY) {
		super(centerX, centerY, radiusX, radiusY);
		color = Color.valueOf("#FFa500FF");

		name = "DragCircle";
	}

	@Override
	public void update() {
		rotationSpeed = (GameEngine.keyboard[KeyCode.LEFT.getCode()] ? -2 : 0) + (GameEngine.keyboard[KeyCode.RIGHT.getCode()] ? 2 : 0);

		MouseEvent mouseEvent;
		if ((mouseEvent = GameEngine.mouse) != null) {
			if (((Path) Shape.intersect(GameEngine.mouseHitBox, ellipse)).getElements().size() > 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
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
